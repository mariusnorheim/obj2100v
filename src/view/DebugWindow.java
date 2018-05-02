package view;

import model.Messagetype;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Observable;
import java.util.Observer;

public class DebugWindow extends JFrame implements Observer {
	
	private static final int W = 500;
	private JTextPane p = new JTextPane();
	private int messagecount;
	static int count = 0;
	private StyleToggler styleToggler = new StyleToggler(); 
	
	public DebugWindow(String title) {
		setTitle(title);
		add(new JScrollPane(p));
		setLocation(200 + (++count * W), 40);
		setSize(W,400);
		setVisible(true);
		p.setContentType("HTML/plain");
	}

	public void write(String s) {
		Document doc = p.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), "\n" + s, styleToggler.next());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Messagetype m = (Messagetype) arg;
		write(" #" + ++messagecount + " " + m.getType());
		write(m.getMsg() + "\n --------------");		
	}
	
}
