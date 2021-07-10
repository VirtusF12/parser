package CoreCluster;

import java.util.ArrayList;
import java.util.List;

public class TreeCluster {

    private String nameTreeCluster;
    private List<Cluster> listClusters;

    public int countClusters(){return listClusters.size();}

    public Cluster getClusterIndex(int index) {
        if (listClusters.size()>0 && index >=0 && index<listClusters.size()) {
            return listClusters.get(index);
        } else return null;
    }

    public boolean addCluster(Cluster cluster) {
        if (listClusters != null) {
            listClusters.add(cluster);
            return true;
        } else
            return false;
    }

    public String getNameTree(){
        return nameTreeCluster;
    }

    public TreeCluster(String nameTreeCluster) {
        this.nameTreeCluster = nameTreeCluster;
        listClusters = new ArrayList<>();
    }
}
