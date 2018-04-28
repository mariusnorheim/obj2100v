package model.stockfish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Stockfish {
    private final String path = System.getProperty("user.dir") + "\\stockfish\\Windows\\stockfish_9_x32.exe";
    private OutputStreamWriter writer;
    private BufferedReader reader;

    public void connect()
    {
        Runtime rt = Runtime.getRuntime();
        Process process = null;

        try {
            process = rt.exec(this.path);
            this.writer = new OutputStreamWriter(process.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // TODO: Add UCI protocol commands
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
