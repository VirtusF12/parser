package CoreVkApiParse.Wall;

import org.json.simple.JSONObject;

public class PostSource {

    private Object type;

    public PostSource(JSONObject jo){

        try {

            this.type =  jo.get("type");
        }  catch(Exception ex) {
            System.out.println("Error: class Like = " + ex.toString());
        }
    }

    public void info() {

        System.out.println("\n\ntype = " + this.type);
    }

}
