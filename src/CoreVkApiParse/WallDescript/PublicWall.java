package CoreVkApiParse.WallDescript;

import java.util.ArrayList;
import java.util.List;

public class PublicWall {

    private List<Wall> wallList;

    public PublicWall(){
        wallList = new ArrayList<>();
    }

    public void add(Wall wall){

         if (wall.getIs_closed().equals("0") && (!(wall.getName().equals("DELETED"))))
            wallList.add(wall);
    }

    public void show(){
        for (int i = 0; i < wallList.size(); i++){
            System.out.println(wallList.get(i).getId());
            System.out.println(wallList.get(i).getIs_closed());
            System.out.println(wallList.get(i).getName());
            System.out.println(wallList.get(i).getScreen_name());
            System.out.println("\n\n");
        }
    }
}
