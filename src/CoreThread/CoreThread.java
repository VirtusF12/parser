package CoreThread;

import java.util.ArrayList;
import java.util.List;

public class CoreThread {

    List<Process> listProcess = new ArrayList<>();

    ProcessMonitor pm;

    double commonTime = 0.0;

    public CoreThread(ProcessMonitor pm) {

        this.pm = pm;
        initListProcess();
        System.out.println("proc.size() = " + listProcess.size());
    }

    private void initListProcess() {

        for (int i = 1; i < pm.getListRange().size(); i+=2){
            int start = pm.getListRange().get(i-1);
            int end = pm.getListRange().get(i);
            System.out.println("start = " + start + ": end = " + end);
            listProcess.add(new Process("process " + i, start, end));
        }
    }

    public void startThreads() {

        for (int i = 0; i < listProcess.size(); i++) {
            listProcess.get(i).start();
        }
    }

    public void showTime () {

        boolean flag = true;

        while (true) {

            flag = true;

            for(int i = 0; i < listProcess.size(); i++) {
                if (listProcess.get(i).sp == StateProcess.STOP)
                    flag &= true;
                else
                    flag &= false;
            }

            if (flag == true)
                break;
        }

        for (int i = 0; i < listProcess.size(); i++)
            this.commonTime += listProcess.get(i).time;
        System.out.println("COMMON TIME IS: " + this.commonTime);
        System.out.println("POCC SIZE IS: " + this.listProcess.size());
    }


    public void info() {

        for (int i = 0; i < listProcess.size(); i++){
            System.out.println(listProcess.get(i).name + " -> (" + listProcess.get(i).begin + "," + listProcess.get(i).end + ")");
        }
    }
}
