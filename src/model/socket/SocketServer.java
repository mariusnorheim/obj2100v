package model.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private int port;
    private ServerSocket ss;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    //private PlayBoard board;

    public SocketServer(int port) {
        this.port = port;
        //board = new PlayBoard();
    }

    @Override
    public void run() {
        System.out.println("Network server socket up.");

        try {
            this.ss = new ServerSocket(this.port);
            Socket cs = ss.accept();
            this.ois = new ObjectInputStream(cs.getInputStream());
            this.oos = new ObjectOutputStream(cs.getOutputStream());

            if (cs.isConnected()) {
                System.out.println("Network client socket connected.");
            }

            while (cs.isConnected()) {
                // Do stuff
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}