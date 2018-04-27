package view;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import model.Messagetype;

public class OutputView extends JFrame implements Observer {
	  private static final int W = 500;
	  private JTextPane p = new JTextPane();
	  private int messagecount;
	  static int count = 0;
	  private StyleToggler styleToggler = new StyleToggler();
	  
	  public OutputView(String title)
	  {
	    setTitle(title);
	    add(new JScrollPane(this.p));
	    setLocation(200 + ++count * 500, 40);
	    setSize(500, 400);
	    setVisible(true);
	    this.p.setContentType("HTML/plain");
	  }
	  
	  public void write(String s)
	  {
	    Document doc = this.p.getStyledDocument();
	    try
	    {
	      doc.insertString(doc.getLength(), "\n" + s, this.styleToggler.next());
	    }
	    catch (BadLocationException e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	  public void update(Observable o, Object arg)
	  {
	    Messagetype m = (Messagetype)arg;
	    write(" #" + ++this.messagecount + " " + m.getType());
	    write(m.getMessage() + "\n --------------");
	  }
}