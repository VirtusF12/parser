package CoreThread;

import java.util.ArrayList;
import java.util.List;

public class ProcessMonitor {

    int from;
    int to;

    int countProcess;

    List<Integer> listRange = new ArrayList<>();

    public ProcessMonitor(int from, int to, boolean isStatic) {
        this.from = from;
        this.to = to;

        setListRange(isStatic);
    }

    public List<Integer> getListRange() {
        return this.listRange;
    }

    private void setListRange(boolean isStatic) {


            if (isStatic) { // is static raspred

                // int from = 0, to = 1000;
                final int countProcess = 600;

                if (countProcess == 1){
                    listRange.add(0);
                    listRange.add(1_000_000);
                } else {

                    for (int i = this.from; i < this.to; ){
                        int start = i;
                        i += to/countProcess;
                        int end = i - 1;
                        // System.out.println("("+(start == 0 ? start+1: start)+","+end+")");
                        listRange.add(start);
                        listRange.add(end);
                    }
                }

            } else {
                // int from = 0;
                // int to = 1000;
                //int countProcess = (to - from) / 100; // где 100 кол-во обрабатываемых постов одним процессом
                //this.countProcess = countProcess;

                executeCountProcess(this.from, this.to);

                for (int i = from; i < to; ){
                    int start = i;
                    i += to/countProcess;
                    int end = i - 1;
                    // System.out.println("("+(start == 0 ? start+1: start)+","+end+")");
                    listRange.add(start);
                    listRange.add(end);
                }
            }


    }

    public void setCountProcess(int countProcess) { // static count process

        this.countProcess = countProcess;
    }

    private void executeCountProcess(int from, int to) { // dynamic exe count process

        final int maxCountGroup = 100;
        this.countProcess = (to - from) / 100;
    }



}
