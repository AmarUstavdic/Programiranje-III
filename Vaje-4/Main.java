import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


// sanpshoting fun :)
public class Main {
    public static void main(String[] args) {
        int threads = Runtime.getRuntime().availableProcessors()-1;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        System.out.println("Available processors: " + threads);


        int n = 1000;
        int a[][] = generateMatrix(n);
        int b[][] = generateMatrix(n);
        int c[][] = new int[n][n];


        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            executorService.submit(new R(i, a, b, c));
        }

        // bad way of doing it but here works fine
        executorService.shutdown();
        try {
            executorService.awaitTermination(1000, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Termination wakeup interrupt failed");
        }
        System.out.println("Done " + (System.currentTimeMillis() - start) + " ms");

    }

    public static int[][] generateMatrix(int n) {
        int matrix[][] = new int[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }

}
