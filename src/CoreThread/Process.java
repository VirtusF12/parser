package CoreThread;

public class Process extends Thread {

    StateProcess sp;
    String name;
    int begin, end;

    double time;

    public double getTimeProcess(){
        return this.time;
    }

    public Process(String name, int begin, int end) {
        this.name = name;
        this.begin = begin;
        this.end = end;

        sp = null;
    }

    @Override
    public void run() {
        execute();
    }

    private void execute(){

        long startTime = System.currentTimeMillis();
        for (int i = this.begin; i < this.end; i++) {
//             System.out.println(this.name + ": " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();

        this.time = ((double)(endTime - startTime)) / 1000;
        sp = StateProcess.STOP;
    }
}
