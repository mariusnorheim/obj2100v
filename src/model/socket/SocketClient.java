package model.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Runnable {
    private String host;
    private int port;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    //PlayBoard board;

    public SocketClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        //board = new PlayBoard();
    }

    @Override
    public void run() {
        try {
            this.socket = new Socket(host, port);
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());

            while (socket.isConnected()) {
                // Do stuff
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}