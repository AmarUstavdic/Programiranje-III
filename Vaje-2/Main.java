import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    static int n = 500;
    static int A[][] = new int[n][n];
    static int B[][] = new int[n][n];
    static int C[][] = new int[n][n];
    static int CP[][] = new int[n][n];

    public static void main(String[] args) {
        generate();

        // vsak loop je O(n), se pravi trije loopi so O(n³)
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        System.out.println("Sequential: " + (System.currentTimeMillis() - start) + " ms");
        //izpisi(C);


        // parallel
        start = System.currentTimeMillis();
        Worker[] workers = new Worker[n];
        for (int i = 0; i < n; i++) {
            workers[i] = new Worker(i);
        }
        for (int i = 0; i < n; i++) {
            workers[i].start();
        }
        for (int i = 0; i < n; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Parallel: " + (System.currentTimeMillis() - start));

    }

    public static void izpisi(int[][] X) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(X[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void generate() {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = random.nextInt(10);
                B[i][j] = random.nextInt(10);
            }
        }
    }

}
