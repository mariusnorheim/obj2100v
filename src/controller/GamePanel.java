import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;


public class GamePanel extends JPanel {
    static final int BOXSIZE = 80;
    private Game game;
    private Box highlighted;

    public GamePanel() throws HeadlessException {
        this.game = GameFactory.getTwoPlayerChessGame();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {


                game.getBoard().getBoard().forEach((p, b) -> {
                    Point pos = mouseEvent.getPoint();
                    pos = new Point(pos.x / BOXSIZE, pos.y / BOXSIZE);
                    if (pos.x == b.getPos().x && pos.y == b.getPos().y) {

                        if(highlighted != null){
                            if(b.getBonde() == null){

                                Bonde BondeToMove = highlighted.getBonde();
                                Shape BondeToMoveGFX =  BondeToMove.getGfx();
                                AffineTransform transform = new AffineTransform();
                                transform.translate(-BondeToMoveGFX.getBounds().x,-BondeToMoveGFX.getBounds().y);
                                transform.translate(pos.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE/3,pos.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE/3);
                                BondeToMoveGFX = transform.createTransformedShape(BondeToMoveGFX);

                                BondeToMove.setGfx(BondeToMoveGFX);
                                b.setBonde(BondeToMove);
                                highlighted.setBonde(null);
                                highlighted = null;


                            }
                        }else {
                            System.out.println(pos.toString());
                            if(b.getBonde() != null){
                                highlighted = b;
                            }



                        }
                        updateUI();
                    }

                });
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        invalidate();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g;


        game.getBoard().getBoard().values().stream().forEach(box -> {
            Point boxpos = box.getPos();
            graphics.setColor(box.getColor());
            graphics.fillRect(boxpos.x * BOXSIZE, boxpos.y * BOXSIZE, BOXSIZE, BOXSIZE);

            if (box.getBonde() == null) {
                return;
            }


            if(box.getBonde().getColor() == Color.WHITE){
                graphics.setColor(Color.BLACK);
                Stroke old = graphics.getStroke();
                graphics.setStroke(new BasicStroke(5));
                graphics.draw(box.getBonde().getGfx());
                graphics.setStroke(old);
                graphics.setColor(box.getBonde().getColor());
                graphics.fill(box.getBonde().getGfx());
            }else if(box.getBonde().getColor() == Color.BLACK){
                graphics.setColor(Color.WHITE);
                Stroke old = graphics.getStroke();
                graphics.setStroke(new BasicStroke(5));
                graphics.draw(box.getBonde().getGfx());
                graphics.setStroke(old);
                graphics.setColor(box.getBonde().getColor());
                graphics.fill(box.getBonde().getGfx());
            }


        });




        if (highlighted != null) {
            graphics.setColor(Color.RED);
            Stroke old = graphics.getStroke();
            graphics.setStroke(new BasicStroke(5));
            graphics.drawRect(highlighted.getPos().x * BOXSIZE, highlighted.getPos().y * BOXSIZE, BOXSIZE, BOXSIZE);
            graphics.setStroke(old);

        }

    }

}
