
/*
   https://oauth.vk.com/authorize?client_id=5490057&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.52
*/

import CoreVkApiParse.AdsWall.AdsPost;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * СБОР ДАННЫХ В ДИАПАЗОНЕ
 */

public class Main {

    public static void main(String[] args) {

        // форматирование json https://jsonformatter.curiousconcept.com
        /**** Connect to MongoDB ****/
        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        /**** Get database ****/
        // if database doesn't exists, MongoDB will create it for you
        DB db = mongo.getDB("monitor-ads");
        /**** Get collection / table from 'testdb' ****/
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table1 = db.getCollection("ads-posts");
        DBCollection table2 = db.getCollection("bad-id");
        DBCollection table3 = db.getCollection("good-id");
        /**** Insert ****/
        // create a document to store key and value
        /*
        BasicDBObject document1 = new BasicDBObject();
        document1.put("id", "test-good-id");
        document1.put("text", "text1");
        document1.put("date-time", new Date());
        table1.insert(document1);
        BasicDBObject document2 = new BasicDBObject();
        document2.put("bad-id", "test-bad-id");
        document2.put("date-time", new Date());
        table2.insert(document2);
        */
        /*
        * 38551860
        * 101827016 мужская атрибутика
        * 9040641
        * 105325163
        * 45745333 4ch
        * информация о группе https://vk.com/dev/wall.get
        * */

        // 2567
        for (int iID = 437; iID <= 4_000; iID++) {
            int innerId = iID;
            try {
                System.out.println(String.valueOf(iID));
                /////////////////////////////////////////////
                final String strIdPublic = "-" + String.valueOf(iID);
                final String verVKApi = "5.85";
                final String strCountPost = "100";
                final String accessToken = "9857c8e61b6dbc4c2bd4a25ad94b4707ebf4ff47ada6a1e23b8972fa20a93d2bfb19f56bcb23996ec3df6";
                final String url = "https://api.vk.com/method/wall.get?owner_id=" + strIdPublic + "&count=" + strCountPost + "&offset=0&access_token=" + accessToken + "&v=" + verVKApi;
                // wall.get — 5000 вызовов в сутки.
                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();


                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String jsonString = response.toString(); // строка от сервера
                System.out.println(jsonString);

                Object objParse = new JSONParser().parse(jsonString); // Считываем json
                JSONObject jsonObj = (JSONObject) objParse;

                JSONObject ja = (JSONObject) jsonObj.get("response");
                JSONArray jaItems = (JSONArray) ja.get("items");
                List<AdsPost> adsPosts = new ArrayList<>();

                for (int i = 0; i < jaItems.size(); i++) {

                    boolean isAds = false, isAttachment = false,
                            isAttachmentPhoto = false, isAttachmentLink = false,
                            isCopyHistory = false, isCopyHistoryPhoto = false, isCopyHistoryVideo = false,
                            isCopyHistoryAtt = false;

                    String glTypeAt = "", glTextAt = "", glTypeCh = "", glTextCh = "";
                    ArrayList<String> glPhotosAt = new ArrayList<>();
                    ArrayList<String> glVideosAt = new ArrayList<>();
                    ArrayList<String> glLinksAt = new ArrayList<>();
                    ArrayList<String> glPhotosCh = new ArrayList<>();
                    ArrayList<String> glVideosCh = new ArrayList<>();
                    ArrayList<String> glLinksCh = new ArrayList<>();
                    JSONObject joTemp = (JSONObject) jaItems.get(i);

                    String marked_as_ads = "\n\n\nРЕКЛАМА: " + joTemp.get("marked_as_ads").toString();
                    String post_type = "ТИП: " + joTemp.get("post_type").toString();
                    String text = joTemp.get("text").toString(); //  "ТЕКСТ: " +

                    // System.out.println(marked_as_ads+"\n"+post_type+"\n"+text); // вывод
                    glTypeAt = post_type;
                    glTextAt = text;

                    if (marked_as_ads.equals("1"))
                        isAds = true;

                    try {
                        JSONArray jaTempAttachment = (JSONArray) joTemp.get("attachments");

                        for (int indexObj = 0; indexObj < jaTempAttachment.size(); indexObj++) { // выполнить перебор по всем объектам списка attachment
                            JSONObject joTempAttPh = (JSONObject) jaTempAttachment.get(indexObj);
                            String currentType = joTempAttPh.get("type").toString(); // получение типа photo video link

                            switch (currentType) {
                                case "photo":
                                    JSONObject joTempAttPhoto = (JSONObject) joTempAttPh.get("photo");
                                    JSONArray jaTempAttPhotoSize = (JSONArray) joTempAttPhoto.get("sizes");

                                    for (int j = 0; j < jaTempAttPhotoSize.size(); j++) { // получение из attachments первого фото "q" типа
                                        JSONObject joSize = (JSONObject) jaTempAttPhotoSize.get(j);
                                        if (joSize.get("type").toString().equals("q")) {
                                            // System.out.println("ССЫЛКА НА ФОТО: " + joSize.get("url").toString() + "\nШИРИНА: " + joSize.get("width").toString() + "\nВЫСОТА: " + joSize.get("height").toString());
                                            glPhotosAt.add("ССЫЛКА НА ФОТО: " + joSize.get("url").toString());
                                            break;
                                        }
                                    }
                                    isAttachmentPhoto = true;
                                    ;
                                    break;
                                case "video":
                                    ;
                                    break;
                                case "link":
                                    JSONObject joTempAttLink = (JSONObject) joTempAttPh.get("link");
                                    String urlLink = "ССЫЛКА: " + joTempAttLink.get("url").toString();
                                    String titleLink = "НАЗВАНИЕ: " + joTempAttLink.get("title").toString();
                                    String descriptionLink = "ОПИСАНИЕ: " + joTempAttLink.get("description").toString();
                                    // System.out.println(urlLink + "\n" + titleLink + "\n" + descriptionLink + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                                    glLinksAt.add(urlLink + "\n" + titleLink + "\n" + descriptionLink + "\n");
                                    isAttachmentLink = true;
                                    ;
                                    break;
                                default:
                                    // System.out.println("--- объектов в attachment не обнаружено");
                                    ;
                                    break;
                            }
                        } // завершение цикла перебора объектов attachment
                        isAttachment = true;

                    } catch (Exception ex) {
                        // System.out.println("нет attachment");
                        isAttachment = false;
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////

                    try { // репост в группе
                        JSONArray jaTempCopyHistory = (JSONArray) joTemp.get("copy_history");

//                    System.out.println("Q->: " + ((JSONObject)jaTempCopyHistory.get(0)).get("text"));

                        for (int indexCH = 0; indexCH < jaTempCopyHistory.size(); indexCH++) { // список copy_history

                            if (((JSONObject) jaTempCopyHistory.get(indexCH)).get("text").equals("") || ((JSONObject) jaTempCopyHistory.get(indexCH)).get("text") == null) {
                                continue;
                            }
                            // System.out.println("Q->: " + ((JSONObject)jaTempCopyHistory.get(indexCH)).get("text"));
                            JSONObject joCopyHistory = (JSONObject) jaTempCopyHistory.get(indexCH);
                            String post_typeCopyHistory = "ТИП CopyHistory: " + joCopyHistory.get("post_type").toString();
                            String textCopyHistory =  joCopyHistory.get("text").toString(); // "ТЕКСТ CopyHistory: " +

                            glTypeCh = post_typeCopyHistory;
                            glTextCh = textCopyHistory;

                            JSONArray jaAttCopyHistoryIn = (JSONArray) joCopyHistory.get("attachments");

                            // begin
                            for (int indexObj = 0; indexObj < jaAttCopyHistoryIn.size(); indexObj++) { // выполнить перебор по всем объектам списка attachment

                                JSONObject joTempAttPh = (JSONObject) jaAttCopyHistoryIn.get(indexObj);
                                String currentType = joTempAttPh.get("type").toString(); // получение типа photo video link

                                switch (currentType) {
                                    case "photo":
                                        JSONObject joTempAttPhoto = (JSONObject) joTempAttPh.get("photo");
                                        JSONArray jaTempAttPhotoSize = (JSONArray) joTempAttPhoto.get("sizes");
                                        for (int j = 0; j < jaTempAttPhotoSize.size(); j++) { // получение из attachments первого фото "q" типа
                                            JSONObject joSize = (JSONObject) jaTempAttPhotoSize.get(j);
                                            if (joSize.get("type").toString().equals("q")) {
                                                // System.out.println("ССЫЛКА НА ФОТО: " + joSize.get("url").toString() + "\nШИРИНА: " + joSize.get("width").toString() + "\nВЫСОТА: " + joSize.get("height").toString());
                                                glPhotosCh.add("ССЫЛКА НА ФОТО: " + joSize.get("url").toString());
                                                // System.out.println("###########PHOTO ADD");
                                                break;
                                            }
                                        }
                                        isCopyHistoryPhoto = true;
                                        ;
                                        break;
                                    case "video":
                                        isCopyHistoryVideo = true;
                                        ;
                                        break;
                                    case "link":
                                        JSONObject joTempAttLink = (JSONObject) joTempAttPh.get("link");
                                        String urlLink = "ССЫЛКА: " + joTempAttLink.get("url").toString();
                                        String titleLink = "НАЗВАНИЕ: " + joTempAttLink.get("title").toString();
                                        String descriptionLink = "ОПИСАНИЕ: " + joTempAttLink.get("description").toString();
                                        // System.out.println(urlLink + "\n" + titleLink + "\n" + descriptionLink + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                                        glLinksCh.add(urlLink + "\n" + titleLink + "\n" + descriptionLink);
                                        ;
                                        break;

                                    default:// System.out.println("--- объектов в attachment не обнаружено");
                                        ;
                                        break;
                                }
                            } // завершение цикла перебора объектов attachment
                            // end

                        } // завершение цикла просмотра списка групп
                        isCopyHistory = true;

                    } catch (Exception ex) {
                        // System.out.println("нет copy_history");
                        isCopyHistory = false;
                    }

                    if (isCopyHistory && isCopyHistoryPhoto) { // репост и фотки в нем (&& isCopyHistoryPhoto)
                        if (glTextCh.length() > 20 || glTextAt.length() > 20) {
                            adsPosts.add(new AdsPost(glTypeAt, (!glTextCh.equals("") ? glTextCh : glTextAt),
                                    (glPhotosCh.size() > 0 ? glPhotosCh : glVideosAt),
                                    (glVideosCh.size() > 0 ? glVideosCh : glVideosAt),
                                    (glLinksCh.size() > 0 ? glLinksCh : glLinksAt)
                            ));
                            adsPosts.get(adsPosts.size() - 1).showAdsPost(); // показать

                            // работа с БД
                            BasicDBObject doc1 = new BasicDBObject();
                            doc1.put("id", String.valueOf(iID));
                            doc1.put("text", (!glTextCh.equals("") ? glTextCh : glTextAt));
                            doc1.put("date-time", new Date());
                            table1.insert(doc1);
                            // прекращение работы с БД
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            innerId = 0;
                        }

                    } else if (isAttachment && isAttachmentPhoto && isAttachmentLink) { // пост и ссылка
                        if (glTextCh.length() > 20 || glTextAt.length() > 20) {
                            adsPosts.add(new AdsPost(glTypeAt, (!glTextCh.equals("") ? glTextCh : glTextAt),
                                    (glVideosAt.size() > 0 ? glVideosAt : glPhotosCh),
                                    (glVideosAt.size() > 0 ? glVideosAt : glVideosCh),
                                    (glLinksAt.size() > 0 ? glLinksAt : glLinksCh)
                            ));
                            // работа с БД
                            BasicDBObject doc1 = new BasicDBObject();
                            doc1.put("id", String.valueOf(iID));
                            doc1.put("text", (!glTextCh.equals("") ? glTextCh : glTextAt));
                            doc1.put("date-time", new Date());
                            table1.insert(doc1);
                            // прекращение работы с БД
                            adsPosts.get(adsPosts.size() - 1).showAdsPost(); // показать
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            innerId = 0;
                        }
                    }
                }
            } catch (IOException ex) {
                // System.out.println("error: IOException");
            } catch (ParseException e) {
                // System.out.println("error: ParseException");
            } catch (Exception ex) {
                // System.out.println("error: Exception (сообщество заблокировано или доступ к нему закрыт)");
//        }  catch (MongoException e) {
//            // System.out.println("MongoException: " + e.toString());
//        }
/////////////////////////////////
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (innerId != 0) {
                BasicDBObject doc2 = new BasicDBObject();
                doc2.put("bad-id", String.valueOf(iID));
                doc2.put("date-time", new Date());
                table2.insert(doc2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                BasicDBObject doc3 = new BasicDBObject();
                doc3.put("good-id", String.valueOf(iID));
                doc3.put("date-time", new Date());
                table3.insert(doc3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

