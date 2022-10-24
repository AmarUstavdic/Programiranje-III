import java.util.Random;

// pass by reference never do static !!!

public class Main {
    static int[] data = new int[69999999];
    static long result;
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        // generating some dummy data
        int n = Runtime.getRuntime().availableProcessors();
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(42123);
        }

        long before = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < data.length ; i++) {
            sum += data[i];
        }
        long after = System.currentTimeMillis();
        System.out.println("End of sequential program, runtime: "+(after-before)+" ms");



        long beforeP = System.currentTimeMillis();
        int chunk = data.length / n;
        Nit[] niti = new Nit[n];
        for (int i = 0; i < niti.length-1; i++) {
            niti[i] = new Nit(i*chunk, i*chunk+chunk);                          // anonymous instance of a class, nimamo poimenovanja in se oravi da smo zgubili referenco do te zadeve
            niti[i].start();
        }
        niti[n-1] = new Nit((n-1)*chunk, data.length);
        niti[n-1].start();

        for (int i = 0; i < niti.length; i++) {
            try {
                niti[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long afterP = System.currentTimeMillis();
        System.out.println("End of parallel   program, runtime: "+(afterP-beforeP)+" ms");


        System.out.println("S: " + sum);
        System.out.println("P: " + result);
    }
}
