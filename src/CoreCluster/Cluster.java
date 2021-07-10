package CoreCluster;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private List<String> mask;
    private String textMask = "";
    private List<String> listPost;
    // private Post post;

    public String getTextMask(){return textMask;}

    public boolean addPost(String textPost) {
        if (mask == null) {
            System.out.println("невозможно добавить в пустой список пост");
            return false;
        } else {
            listPost.add(textPost);
            return true;
        }
    }

    public boolean addMask(String unionWord) {
        if (mask == null) {
            System.out.println("невозможно добавить в пустой список маски");
            return false;
        } else {
            mask.add(unionWord);
            textMask += unionWord;
            return true;
        }
    }

    public void showAllPosts(){
        for(String str : listPost) {
            System.out.println(str);

        } System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public Cluster() {
        this.mask = new ArrayList<>();
        listPost = new ArrayList<>();
    }

}
