package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import controller.InputController;

public class InputView extends JFrame {
	  private InputController inputcontroller;
	  
	  public InputView()
	  {
	    setTitle("user input");
	    add(new JScrollPane(this.inputcontroller = new InputController()));
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