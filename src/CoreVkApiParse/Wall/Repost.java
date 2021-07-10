package CoreVkApiParse.Wall;

import org.json.simple.JSONObject;

public class Repost {

    private Object count, user_reposted;

    public Repost(JSONObject jo){

        try {

            this.count =  jo.get("count");
            this.user_reposted = jo.get("user_reposted");

        }  catch(Exception ex) {
            System.out.println("Error: class Like = " + ex.toString());
        }
    }

    public void info() {

        System.out.println("\n\ncount = " + this.count);
        System.out.println("user_reposted = "  + this.user_reposted);
    }

}
