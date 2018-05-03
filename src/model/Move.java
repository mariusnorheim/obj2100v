package model;

import model.socket.SocketClient;
import model.stockfish.processbuilderstrategy.*;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Move {
    private String newmove;
    private String history = "";

    public String getHistory() {
        return history;
    }

    public Move(String newmove) {
        this.newmove = newmove;

        newMove(newmove);

        this.history = getHistory() + newmove + " ";
    }

    public void newMove(String newmove) {
        SocketClient client = new SocketClient("localhost", 5134);
        client.sendMove(newmove);

            /*
            StockfishProcess stockfish = new StockfishProcess(reader);
            String history = getHistory();

            */
    }
}
