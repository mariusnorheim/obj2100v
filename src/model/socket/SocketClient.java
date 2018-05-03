package model.socket;

import model.Messagetype;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Runnable {
    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // Create the socket
            this.socket = new Socket(host, port);
            // Create output and input stream to the server
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMove(String newmove) {
        try {
            // Send object to server
            Messagetype msg = new Messagetype("move", newmove);
            this.oos.writeObject(msg);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}