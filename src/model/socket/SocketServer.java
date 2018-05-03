package model.socket;

import model.Messagetype;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // Create the server socket
            this.serverSocket = new ServerSocket(this.port);

            while(true) {
                // Create the client socket
                this.clientSocket = serverSocket.accept();
                System.out.println("Network server socket up");

                // Create output and input stream to the client
                this.ois = new ObjectInputStream(clientSocket.getInputStream());
                this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void getMove() {
        try {
            // Read object from server
            String move = (String) ois.readObject();
            System.out.println("New move is: " + move);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}