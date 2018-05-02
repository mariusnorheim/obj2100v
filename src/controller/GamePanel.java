package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

public class GamePanel extends JPanel {
    public static final int BOXSIZE = 80;
    private Game game;
    private Box highlighted;
    String fen = "";

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
                            if(b.getSpillebrikke() == null){

                                Spillebrikke SpillebrikkeToMove = highlighted.getSpillebrikke();
                                Shape SpillebrikkeToMoveGFX =  SpillebrikkeToMove.getGfx();
                                AffineTransform transform = new AffineTransform();
                                transform.translate(-SpillebrikkeToMoveGFX.getBounds().x,-SpillebrikkeToMoveGFX.getBounds().y);
                                transform.translate(pos.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE/3,pos.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE/3);
                                SpillebrikkeToMoveGFX = transform.createTransformedShape(SpillebrikkeToMoveGFX);

                                SpillebrikkeToMove.setGfx(SpillebrikkeToMoveGFX);
                                b.setSpillebrikke(SpillebrikkeToMove);
                                highlighted.setSpillebrikke(null);
                                highlighted = null;


                            }
                        }else {
                            
                            int x = (int)pos.getX();
                        	int y = (int)pos.getY();
                        	
                        	// omgjør point to fen kode og skriver den ut. 
                        	// forløbig bare på første klikk.
                        	PointToFen fen = new PointToFen();
                        	fen.setPointToFen(x, y);
                        	
                    		//System.out.print(fen);
                            
                            if(b.getSpillebrikke() != null){
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

            if (box.getSpillebrikke() == null) {
                return;
            }


            if(box.getSpillebrikke().getColor() == Color.WHITE){
                graphics.setColor(Color.BLACK);
                Stroke old = graphics.getStroke();
                graphics.setStroke(new BasicStroke(5));
                graphics.draw(box.getSpillebrikke().getGfx());
                graphics.setStroke(old);
                graphics.setColor(box.getSpillebrikke().getColor());
                graphics.fill(box.getSpillebrikke().getGfx());
            }else if(box.getSpillebrikke().getColor() == Color.BLACK){
                graphics.setColor(Color.WHITE);
                Stroke old = graphics.getStroke();
                graphics.setStroke(new BasicStroke(5));
                graphics.draw(box.getSpillebrikke().getGfx());
                graphics.setStroke(old);
                graphics.setColor(box.getSpillebrikke().getColor());
                graphics.fill(box.getSpillebrikke().getGfx());
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
