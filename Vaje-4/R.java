public class R implements Runnable{
    private int i;
    private int a[][];
    private int b[][];
    private int c[][];

    public R(int i, int[][] a, int[][] b, int[][] c) {
        this.i = i;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void run() {
        for (int j = 0; j < a.length; j++) {
            for (int k = 0; k < b.length; k++) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
}
