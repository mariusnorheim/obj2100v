package gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class InputWindow extends JFrame {
	  private Inputpane inputpane;
	  
	  public InputToStockfish()
	  {
	    setTitle("input to engine");
	    add(new JScrollPane(this.inputpane = new Inputpane()));
	    add(new JButton(new AbstractAction("stop")
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        System.exit(0);
	      }
	    }), "South");
	    
	    setBounds(100, 100, 500, 300);
	    setVisible(true);
	  }
}
