package CoreKMeans;

import java.util.ArrayList;
import java.util.List;

public class KMeans {

    private static List<List<Point2D>> clustersGeneral;

    // рандомная инициализация центра
    public static List<Point2D> initializeRandomCenters(int n, int lowerBound, int upperBound) {
        List<Point2D> centers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            float x = (float)(Math.random() * (upperBound - lowerBound) + lowerBound);
            float y = (float)(Math.random() * (upperBound - lowerBound) + lowerBound);
            Point2D point = new Point2D(x, y);
            centers.add(point);
        }
        return centers;
    }

    // метод расчета новых центров
    public static List<Point2D> getNewCenters(List<Point2D> dataset, List<Point2D> centers) {

        // создание списка со списками кластеров
        List<List<Point2D>> clusters = new ArrayList<>(centers.size());
        clustersGeneral = clusters;

        // добавление в список двух кластеров
        for (int i = 0; i < centers.size(); i++) {
            clusters.add(new ArrayList<Point2D>());
        }

        for (Point2D data : dataset) {
            // получние индекса кластера
            int index = data.getNearestPointIndex(centers);
            clusters.get(index).add(data); // получение списка (кластер) и добавление в него точки
        }

        // создание новых центров
        List<Point2D> newCenters = new ArrayList<>(centers.size());

        for (List<Point2D> cluster : clusters) {
            newCenters.add(Point2D.getMean(cluster));
        }
        return newCenters;
    }

    public static double getDistance(List<Point2D> oldCenters, List<Point2D> newCenters) {
        double accumDist = 0;
        for (int i = 0; i < oldCenters.size(); i++) {
            double dist = oldCenters.get(i).getDistance(newCenters.get(i));
            accumDist += dist;
        }
        return accumDist;
    }

    public static List<Point2D> kmeans(List<Point2D> centers, List<Point2D> dataset, int k) {

        boolean converged; // сходимость - когда расстояние сопостовимо с нулем
        do {
            List<Point2D> newCenters = getNewCenters(dataset, centers);
            double dist = getDistance(centers, newCenters);
            centers = newCenters;
            converged = dist == 0;

            if (converged == true) {
                System.out.println("кластеризация закончена");
            }


        } while (!converged);
        return centers;
    }

    public static void main(String[] args) {

        System.out.println("k-means");
        int k = 2;

        Point2D p1 = new Point2D(185f, 72f);
        Point2D p2 = new Point2D(170f, 56f);
        Point2D p3 = new Point2D(168f, 60f);
        Point2D p4 = new Point2D(179f, 68f);
        Point2D p5 = new Point2D(182f, 72f);
        Point2D p6 = new Point2D(188f, 77f);

        List<Point2D> dataset = new ArrayList<>();
        dataset.add(p1);
        dataset.add(p2);
        dataset.add(p3);
        dataset.add(p4);
        dataset.add(p5);
        dataset.add(p6);

        // List<Point2D> centers = initializeRandomCenters(k, 70, 150);

        List<Point2D> centers = new ArrayList<>();
        centers.add(new Point2D(185f, 72f));
        centers.add(new Point2D(170f, 56f));

        long start = System.currentTimeMillis();
        kmeans(centers, dataset, k);

        if (clustersGeneral != null) {
            for (List<Point2D> cluster : clustersGeneral) {

                for (Point2D point : cluster) {
                    System.out.print(point + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("clustersGeneral == null");
        }

        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start) + "ms");
    }
}
