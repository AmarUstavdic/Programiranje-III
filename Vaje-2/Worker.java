public class Worker extends Thread {
    private  int i;

    public Worker(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < Main.n; j++) {
            for (int k = 0; k < Main.n; k++) {
                Main.CP[i][j] += Main.A[i][k] * Main.B[k][j];
            }
        }
    }
}
