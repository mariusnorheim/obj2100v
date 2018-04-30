package model.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream ois;

    public SocketServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // Create the server socket
            this.serverSocket = new ServerSocket(this.port);
            // Create the client socket
            this.clientSocket = serverSocket.accept();
            System.out.println("Network server socket up");
            // Create input stream to the client
            this.ois = new ObjectInputStream(clientSocket.getInputStream());

            while(true) {
                SocketMessage msg = (SocketMessage) ois.readObject();
                String newmove = msg.getMessage();
                System.out.println("New move is: " + newmove);
                // TODO: Pass move to swordfish
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}