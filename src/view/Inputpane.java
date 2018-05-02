package view;

import model.stockfish.processbuilderstrategy.StockfishProcess;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;


public class Inputpane extends JTextPane {
	
	/*
	 * A short example. Remember to press enter (in the Inputpane) after the input.
	 */
	
	// Use next example for marshalling:
	// position fen rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
	// The, use just the letter d for character display:
	// d
	
	
	private PrintWriter pw;

	public Inputpane() {
		DebugWindow responses = new DebugWindow("responses");
		try {
			PipedWriter pipedwriter;
			BufferedReader reader = new BufferedReader(new PipedReader(pipedwriter = new PipedWriter()) );
			pw = new PrintWriter(pipedwriter);
			StockfishProcess stockfish = new StockfishProcess(reader);
			stockfish.addObserver(responses);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					talkToStockfish();			
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});				
	}
	
	
	private void talkToStockfish() {
		String[] a = getText().split("\n");
		pw.print((a[a.length - 1]));
	}

}
