package CoreVkApiParse.Wall;

import org.json.simple.JSONObject;

public class VkPost {

    private Photo photo;
    private PostSource postSource;
    private Comment comment;
    private Like like;
    private Repost repost;

    public VkPost( Photo photo,
             PostSource postSource,
             Comment comment,
             Like like,
             Repost repost) {
        this.photo = photo;
        this.postSource = postSource;
        this.comment = comment;
        this.like = like;
        this.repost = repost;
    }


    public void info(){

        this.photo.info();
        this.postSource.info();
        this.comment.info();
        this.like.info();
        this.repost.info();
    }


}
