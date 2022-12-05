import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private Socket connection;
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson;

    public Client(Socket connection) {
        this.gson = new Gson();
        this.connection = connection;
        try {
            this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("Connection established with: " + connection.getInetAddress());
        String line;
        try {
            while ((line = in.readLine()) != null) {
                System.out.println(connection.getInetAddress() + " Recieved message: " + line);
                Message message = gson.fromJson(line, Message.class);
                message.print();
            }
        } catch (Exception e) {
            System.out.println("Protocol violation. Closing connection!");
            try {
                in.close();
                out.close();
                connection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }




    }
}
