package model.stockfish.processbuilderstrategy;

import model.Messagetype;
import model.socket.SocketClient;
import model.stockfish.SuperStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ClientThread extends SuperStrategy  {

	private StockfishProcess stockfishProcess;

	public ClientThread(String processFilename, StockfishProcess stockfishProcess, BufferedReader br) {
		this.stockfishProcess = stockfishProcess;
		commandReader = br;
		ProcessBuilder pb = new ProcessBuilder(processFilename);
		try {
			Process process = pb.start();
			processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			processWriter = new OutputStreamWriter(process.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	protected void talkToStockfish(String history, String move) {
		try {
			// Stockfish gets move
			if(history.equals("")) {
				System.out.println("Attempting to registering move: " + move);
				processWriter.write("position startpos moves " + move + "\n");
				processWriter.flush();
				processWriter.write("isready\n");
				processWriter.flush();
			} else {
				System.out.println("Attempting to registering move: " + history + " " + move);
				processWriter.write("position startpos moves " + history + " " + move + "\n");
				processWriter.flush();
				processWriter.write("isready\n");
				processWriter.flush();
			}

			// Nothing to display here
			String l = "";
			String result = "";
			while (!(l = processReader.readLine()).equals("readyok")) {
				result += l + "\n";
			}

			// Stockfish gets current chessboard
			processWriter.write("d\n");
			processWriter.flush();
			processWriter.write("isready\n");
			processWriter.flush();

			// Stockfish returns current chessboard
			l = "";
			result = "";
			while (!(l = processReader.readLine()).equals("readyok")) {
				result += l + "\n";
			}

			stockfishProcess.update(new Messagetype("board", result));

			// Send object - not working
			//SocketClient client = new SocketClient("localhost", 5134);
			//client.sendMove(move);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
