import model.socket.SocketClient;
import model.socket.SocketServer;

public class SocketStart {
    public static void main(String[] args) {
        // Do stuff
        SocketServer server = new SocketServer(5134);
        SocketClient client = new SocketClient("localhost", 5134);

        server.run();
        client.run();
        client.sendMove("e2e4");
        client.sendMove("a7a5");
    }
}