package model.stockfish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


public abstract class SuperStrategy extends Thread {

	protected BufferedReader commandReader;
	protected BufferedReader processReader;
	protected OutputStreamWriter processWriter;
	
	int count = 0;
	
	public void run() {
		Boolean firstmove = true;
		String command;
		String[] history;
		String move;
		try {
			while ((command = commandReader.readLine()) != null) {
				history = command.split(" ");
				move = history[history.length-1];

				if (!command.isEmpty()) {
					System.out.println(history.length);

					if(firstmove) {
						talkToStockfish("", move);
						firstmove = false;
					} else {
						talkToStockfish(history[0], move);
					}
				}
				else System.out.println("command was empty, was not sent to stockfish");
				System.out.println("Command #" + ++count + " [" + command + "] length " + command.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}

	abstract protected void talkToStockfish(String history, String move);
	
}
