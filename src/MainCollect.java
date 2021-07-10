import CoreVkApiParse.AdsWall.AdsPost;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// http://j4sq.blogspot.com/2011/11/java-mongodb-tutorial.html

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;


/**
 * ПОЛУЧЕНИЕ СОБРАННЫХ ДАННЫХ ДЛЯ АНАЛИЗА
 */

public class MainCollect {

    private static ArrayList<String> getListWord(String data) {
        String result = data.replaceAll("[-+!^./:,\b\\t\\n\\f\\']"," ").toLowerCase();
        String[] arr = result.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)
            if (arr[i].length() > 2)
                list.add(arr[i]);
        return list;
    }

    private static void showListWord(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + "("+list.get(i).length()+") ");
    }

    private static String getWord(String data) {
        String result = data.replaceAll("[-+!^./:,\b\\t\\n\\f\\']"," ").toLowerCase();
        String[] arr = result.split(" ");
        String str = "";
        for (int i = 0; i < arr.length; i++)
            if (arr[i].length() > 2)
                str += arr[i] + " ";
        return str;
    }

    public static void main(String[] args) {
        System.out.println("--> start <--");

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // объект db подключение к MongoDB серверу для указанной базы данных
        DB db = mongoClient.getDB("monitor-ads");
        // проверка существования коллекции
        if (db.collectionExists("ads-posts")) {
            int count = 0;
            DBCollection dbColl = db.getCollection("ads-posts");
            // получение всех объектов коллекции через метод find()
            DBCursor cursor = dbColl.find();
            while (cursor.hasNext()) {
                if (count == 50) break;
                DBObject dbObject = cursor.next();
                String strBadID = (String) dbObject.get("text");

                // фильтр Портера
                strBadID = getWord(strBadID);

                System.out.println(strBadID);
                System.out.println("-------------------------------\n");
                count += 1;
            }

        } else System.out.println("no connection: bad-id");


        // уничтожение экземпляра для очистки ресурса
        mongoClient.close();
        System.out.println("--> end <--");
    }
}

// получние списка колекций
/*
        Set<String> setColl = db.getCollectionNames();
        for(String nameColl : setColl)
        {
            System.out.println(nameColl);
        }

        System.out.println("count="+dbColl.count());
*/
