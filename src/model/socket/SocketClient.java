package model.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient implements Runnable {
    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream oos;

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // Create the socket
            this.socket = new Socket(host, port);
            // Create output stream to the server
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void newMove(String move) {
        try {
            // Send object to server
            SocketMessage msg = new SocketMessage("move", move);
            oos.writeObject(msg);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}