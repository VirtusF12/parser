import java.util.ArrayList;
import java.util.List;

import CoreThread.*;

public class Main2Thread {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        ProcessMonitor pm1 = new ProcessMonitor(0, 1_000_000, true);

        CoreThread ct = new CoreThread(pm1);
        // ct.info();
        ct.startThreads();




        ct.showTime();


        long endTime = System.currentTimeMillis();

        double seconds = ((double)(endTime - startTime)) / 1000;

        System.out.println(seconds + " sec");


        // подача диапазона значений
       // int from = 0;
       // int to = 32_933;

        // ProcessMonitor pm = new ProcessMonitor();

        // выполнить распределение нагрузки между процессами

        // запустить на выполнение решения задачи каждым процессом



    }
}
