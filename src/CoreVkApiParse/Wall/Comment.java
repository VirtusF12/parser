package CoreVkApiParse.Wall;

import org.json.simple.JSONObject;

public class Comment {

    private Object count, can_post;

    public Comment(JSONObject jo){

        try {

            this.count =  jo.get("count");
            this.can_post = jo.get("can_post");
        }  catch(Exception ex) {
            System.out.println("Error: class Like = " + ex.toString());
        }
    }

    public void info() {

        System.out.println("\n\ncount = " + this.count);
        System.out.println("can_post = "  + this.can_post);
    }

}
