package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import javax.swing.JTextPane;
import view.OutputView;
import controller.InputController;
import model.commandbuilderstrategy.StockfishProcess;

public class InputController extends JTextPane {
	  private PrintWriter pw;
	  
	  public InputController()
	  {
	    OutputView output = new OutputView("output");
	    try
	    {
	      PipedWriter pipedwriter;
	      BufferedReader reader = new BufferedReader(new PipedReader(pipedwriter = new PipedWriter()));
	      this.pw = new PrintWriter(pipedwriter);
	      StockfishProcess stockfish = new StockfishProcess(reader);
	      stockfish.addObserver(output);
	    }
	    catch (IOException e1)
	    {
	      e1.printStackTrace();
	    }
	    addKeyListener(new KeyListener()
	    {
	      public void keyTyped(KeyEvent e)
	      {
	        if (e.getKeyChar() == '\n') {
	          InputController.this.talkToStockfish();
	        }
	      }
	      
	      public void keyReleased(KeyEvent e) {}
	      
	      public void keyPressed(KeyEvent e) {}
	    });
	  }
	  
	  private void talkToStockfish()
	  {
	    String[] a = getText().split("\n");
	    this.pw.print(a[(a.length - 1)]);
	  }
}