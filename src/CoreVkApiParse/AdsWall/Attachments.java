package CoreVkApiParse.AdsWall;

import java.util.ArrayList;
import java.util.List;

public class Attachments {

    private List<String> photos, videos, links;

    public Attachments(ArrayList<String> photos, ArrayList<String> videos, ArrayList<String> links) {
        photos = new ArrayList<>();
        videos = new ArrayList<>();
        links = new ArrayList<>();

        this.photos = photos;
        this.videos = videos;
        this.links = links;
    }

    // получить
    public String getPhotoUrl(int index){
        String res = "";
        try {
            res = photos.get(index);
        } catch (Exception ex) {
            System.out.println("нет фото по данному индексу");
        }
        return res;
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
}
