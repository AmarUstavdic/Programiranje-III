public class Message {
    Protocol protocol;
    String body;

    public Message(Protocol protocol, String body) {
        this.protocol = protocol;
        this.body = body;
    }


    public void print() {
        System.out.println("Protocol: " + protocol + " Payload: " + body);
    }


}
