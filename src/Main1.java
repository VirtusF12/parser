

/*
    назначение параметров https://vk.com/dev/objects/group
 */


import CoreVkApiParse.Wall.*;
import CoreVkApiParse.WallDescript.PublicWall;
import CoreVkApiParse.WallDescript.Wall;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main1 {

    static String generateRow(int n){

        String res = "";

        int size = n;
        for (int i = 1; i <= size;i++){
            if (i == size)
                res += String.valueOf(i);
            else
                res += String.valueOf(i)+",";
        }
        return res;
    }

    static String generateRow(int start, int end){

        String res = "";

        int size = end;
        for (int i = start; i <= size;i++){
            if (i == size)
                res += String.valueOf(i);
            else
                res += String.valueOf(i)+",";
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println("--- получение id пабликов вконтакте");

        // final String groupsGetByID = "105325163,1,vecherniy.urgant,172029999,4234234,23423";

        int glStart = 1, glStop = 1_111_343;
        final int lim = 500;
        for (int j = lim; j <= ((lim - (glStop%lim))+glStop); j+=lim) {


            int start = j-lim+1, end = j;

            // System.out.print(start + ":" + end + " | ");

            //~~~~~~~~~~~~~~~~~~~~~~ start
            //int start = 300;
            // int end = 520;
            String groupsGetByID = generateRow(start,end);

            final String verVKApi = "5.85";
            final String accessToken = "7c1bb773ee4951b58ca8057b8bc1f0eb33340ecd5137ed9f585a0453bd3125a65ca6e786989a2b7aeb443";
            final String url = "https://api.vk.com/method/groups.getById?group_ids="+groupsGetByID+"&access_token="+accessToken+"&v="+verVKApi;

            try {
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

                String jsonString = response.toString();

                Object objParse = new JSONParser().parse(jsonString);
                JSONObject jsonObj = (JSONObject) objParse;
                JSONArray ja =  (JSONArray) jsonObj.get("response");

                PublicWall publicWall = new PublicWall();

                for (int i = 0; i < (end-start); i++){

                    JSONObject jo = (JSONObject) ja.get(i);

                    publicWall.add(new Wall( (jo.get("id")).toString(),(jo.get("name")).toString(),
                            (jo.get("screen_name")).toString(),jo.get("is_closed").toString() ));

                }

                publicWall.show();

            }   catch (IOException ex){
            }   catch (ParseException e) {
                e.printStackTrace();
            }
            //~~~~~~~~~~~~~~~~~~~~~~ stop

        }



        System.out.println("--- завершение");
    }
}
