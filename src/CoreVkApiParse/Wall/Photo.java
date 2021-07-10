package CoreVkApiParse.Wall;

import org.json.simple.JSONObject;

public class Photo {

    private Object id, album_id, owner_id, user_id, photo_75, photo_130, photo_604, width, height, text, date, post_id, access_key;

    public Photo(JSONObject jo){

        try {

            this.id =  jo.get("id");
            this.album_id = jo.get("album_id");
            this.owner_id =  jo.get("owner_id");
            this.user_id = jo.get("user_id");
            this.photo_75 =  jo.get("photo_75");
            this.photo_130 = jo.get("photo_130");
            this.photo_604 =  jo.get("photo_604");
            this.width = jo.get("width");
            this.height = jo.get("height");
            this.text = jo.get("text");
            this.date = jo.get("date");
            this.post_id = jo.get("post_id");
            this.access_key = jo.get("access_key");

        }  catch(Exception ex) {
            System.out.println("Error: class Like = " + ex.toString());
        }
    }

    public void info() {

        System.out.println("\n\nid = " + this.id);
        System.out.println("album_id = "  + this.album_id);
        System.out.println("owner_id = "  + this.owner_id);
        System.out.println("user_id = "  + this.user_id);
        System.out.println("photo_75 = "  + this.photo_75);
        System.out.println("photo_130 = "  + this.photo_130);
        System.out.println("photo_604 = "  + this.photo_604);
        System.out.println("width = "  + this.width);
        System.out.println("height = "  + this.height);
        System.out.println("text = "  + this.text);
        System.out.println("date = "  + this.date);
        System.out.println("post_id = "  + this.post_id);
        System.out.println("access_key = "  + this.access_key);

    }

}
