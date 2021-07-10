package CoreVkApiParse.Wall;

import org.json.simple.JSONObject;

public class Like {

    private Object count, user_likes, can_like, can_publish;

    public Like(JSONObject jo){

        try {

            this.count =  jo.get("count");
            this.user_likes = jo.get("user_likes");
            this.can_like =  jo.get("can_like");
            this.can_publish = jo.get("can_publish");
        }  catch(Exception ex) {
            System.out.println("Error: class Like = " + ex.toString());
        }
    }

    public void info() {

        System.out.println("\n\ncount = " + this.count);
        System.out.println("user_likes = "  + this.user_likes);
        System.out.println("can_like = " + this.can_like);
        System.out.println("can_publish = " + this.can_publish);
    }

    /*
            System.out.println("\n\ncount = " + joLike.get("count"));
            System.out.println("user_likes = "  + joLike.get("user_likes"));
            System.out.println("can_like = " + joLike.get("can_like"));
            System.out.println("can_publish = " + joLike.get("can_publish"));
            */
}
