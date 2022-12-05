import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Message message = new Message(Protocol.CHAT_MESSAGE, "Hello this is first message!");
        System.out.println(new Gson().toJson(message));
        ExecutorService executorService = Executors.newCachedThreadPool();


        try {
            ServerSocket serverSocket = new ServerSocket(1337);
            while (true) {
                Client c = new Client(serverSocket.accept());
                executorService.submit(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
