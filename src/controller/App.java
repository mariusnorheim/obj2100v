import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setBackground(Color.GRAY);
        frame.setVisible(true);
        GamePanel a = new GamePanel();
        a.setSize(frame.getWidth(),frame.getHeight());
        frame.add(a);
        a.setVisible(true);
    }
}
