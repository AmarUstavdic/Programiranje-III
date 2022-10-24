public class Nit extends Thread {
    private int start;
    private int end;

    public Nit(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public void run() {
        long partialSum = 0;
        for (int i = start; i < end; i++) {
            partialSum += Main.data[i];
        }
        Main.result += partialSum;
    }
}
