package CoreVkApiParse.AdsWall;

import java.util.ArrayList;
import java.util.List;

public class AdsPost {

    private String post_type, text;
    private List<String> photos, videos, links;

    public AdsPost(String post_type, String text, ArrayList<String> photos, ArrayList<String> videos, ArrayList<String> links) {

        this.post_type = post_type;
        this.text = text;

        this.photos = photos;
        this.videos = videos;
        this.links = links;
    }

    public String getText() {
        return text;
    }

    public void showAdsPost(){
        System.out.println(post_type);
        System.out.println(text);
        if (photos.size()>0){
            String strPhotos = "";
            for (int i = 0;i<photos.size();i++)
                strPhotos += photos.get(i)+"\n";
            System.out.println(strPhotos);
        }

        // if (videos.size()>0)
        if (links.size()>0){
           String strLinks = "";
           for(int i=0; i < links.size(); i++)
               strLinks += links.get(i)+"\n";
            System.out.print(strLinks);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
    }

    public String getVideoUrl(int index){
        String res = "";
        try {
            res = videos.get(index);
        } catch (Exception ex) {
            System.out.println("нет видео по данному индексу");
        }
        return res;
    }

    public String getLinkUrl(int index){
        String res = "";
        try {
            res = links.get(index);
        } catch (Exception ex) {
            System.out.println("нет ссылки по данному индексу");
        }
        return res;
    }

    // добавить
    public void addPhoto(String strUrlPhoto){
        photos.add(strUrlPhoto);
    }

    public void addVideo(String strUrlVideo){
        videos.add(strUrlVideo);
    }

    public void addLink(String strUrlLink){
        links.add(strUrlLink);
    }

//    private Attachments attachments;
//    private CopyHistory copyHistory;
//
//    public AdsPost(String post_type, String text, Attachments attachments, CopyHistory copyHistory){
//        this.post_type = post_type;
//        this.text = text;
//        this.attachments = attachments;
//        this.copyHistory = copyHistory;
//    }
}
