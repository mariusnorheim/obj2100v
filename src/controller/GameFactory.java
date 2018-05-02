package controller;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GameFactory {
	public static Game getTwoPlayerChessGame() {
        HashMap<Point, Box> board = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Point cur = new Point(i, j);
                if (j % 2 == 0) {
                    if (i % 2 == 0) {
                        board.put(cur, new Box(cur, Color.WHITE));
                    } else if (i % 2 == 1) {
                        board.put(cur, new Box(cur, Color.BLACK));
                    }
                } else {
                    if (i % 2 == 1) {
                        board.put(cur, new Box(cur, Color.WHITE));
                    } else if (i % 2 == 0) {
                        board.put(cur, new Box(cur, Color.BLACK));
                    }
                }

            }
        }


        //KONGE
        int[] KONGEX = {10, 20, 22, 22, 24, 25, 25, 24, 22, 20, 18, 17, 15,
                13, 12, 10, 8, 6, 5, 5, 6, 8, 8, 10};
        int[] KONGEY = {5, 5, 6, 10, 13, 15, 17, 18, 20, 20, 18, 22, 23,
                22, 18, 20, 20, 18, 17, 15, 13, 10, 6, 5};

         int bondeX[]={ 5,11,10,11,12,15,18,19,20,19,25};
         int bondeY[]={30,18,15,12,11,10,11,12,15,18,30};

         int kongeX[]={ 5, 8, 8, 5, 6, 9,13,14,14,12,12,14,14,16,16,18,18,16,16,17,21,24,25,22,22,25};
         int kongeY[]={30,24,17,11, 8, 6, 5, 5, 4, 4, 2, 2, 0, 0, 2, 2, 4, 4, 5, 5, 6, 8,11,17,24,30};

         int springerX[]={ 5, 8, 8, 8,12, 6, 5,14,15,16,17,18,21,24,25,22,22,25};
         int springerY[]={30,24,17,17,13,14,11, 5, 3, 5, 3, 5, 6, 8,11,17,24,30};

         int dronningX[]={ 5, 8, 8, 5, 5, 9,13,15,17,21,25,25,22,22,25};
         int dronningY[]={30,24,17,11, 3, 6, 5, 2, 5, 6, 3,11,17,24,30};

         int løperX[]={ 5, 8, 8, 5, 6, 9,10,12,15,13,17,21,24,25,22,22,25};
         int løperY[]={30,24,17,11, 8, 6, 6,11,10, 5, 5, 6, 8,11,17,24,30};

         int tårnX[]={ 5, 8, 8, 5, 5, 9, 9,13,13,17,17,21,21,25,25,22,22,25};
         int tårnY[]={30,24,17,11, 6, 6, 9, 9, 6, 6, 9, 9, 6, 6,11,17,24,30};

        Set<Bonde> allBondes = new HashSet<>();


        Point cur = new Point(3, 0);

        int size = GamePanel.BOXSIZE / 3;
        

        Bonde p = new Konge(Color.BLACK);

        int curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        int curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        Shape a = new Polygon(KONGEX, KONGEY, KONGEX.length);
        AffineTransform transform = new AffineTransform();
        transform.translate(-a.getBounds().x, -a.getBounds().y);
        transform.translate(curX, curY);
        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);


        cur = new Point(3, 7);


        p = new Konge(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(KONGEX, KONGEY, KONGEX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);



        cur = new Point(4, 7);


        p = new Dronning(Color.WHITE);



        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(dronningX, dronningY, dronningX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);


        cur = new Point(4, 0);


        p = new Dronning(Color.BLACK);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(dronningX, dronningY, dronningX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);




        // Bonde
        for(int i = 0 ; i < 8; i++){
            cur = new Point(i, 1);
            p = new Bonde(Color.BLACK);

            curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
            curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

            a = new Polygon(bondeX, bondeY, bondeX.length);
            transform = new AffineTransform();
            transform.translate(curX, curY);
            transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

            a = transform.createTransformedShape(a);

            p.setGfx(a);
            board.get(cur).setBonde(p);
            p.setActive(true);
            allBondes.add(p);

        }

        for(int i = 0 ; i < 8; i++){
            cur = new Point(i, 6);
            p = new Bonde(Color.WHITE);

            curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
            curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

            a = new Polygon(bondeX, bondeY, bondeX.length);
            transform = new AffineTransform();
            transform.translate(curX, curY);
            transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

            a = transform.createTransformedShape(a);

            p.setGfx(a);
            board.get(cur).setBonde(p);
            p.setActive(true);
            allBondes.add(p);
        }


            cur = new Point(0, 0);
            p = new Tårn(Color.BLACK);

            curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
            curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

            a = new Polygon(tårnX, tårnY, tårnX.length);
            transform = new AffineTransform();
            transform.translate(curX, curY);
            transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

            a = transform.createTransformedShape(a);

            p.setGfx(a);
            board.get(cur).setBonde(p);
            p.setActive(true);
            allBondes.add(p);


        cur = new Point(7, 0);
        p = new Tårn(Color.BLACK);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(tårnX, tårnY, tårnX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);

        cur = new Point(7, 7);
        p = new Tårn(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(tårnX, tårnY, tårnX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);


        cur = new Point(0, 7);
        p = new Tårn(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(tårnX, tårnY, tårnX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);



        cur = new Point(2, 7);
        p = new Tårn(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(løperX, løperY, løperX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);

        cur = new Point(5, 7);
        p = new Tårn(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(løperX, løperY, løperX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);



        cur = new Point(2, 7);
        p = new Løper(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(løperX, løperY, løperX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);

        cur = new Point(2, 0);
        p = new Løper(Color.BLACK);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(løperX, løperY, løperX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);


        cur = new Point(5, 0);
        p = new Løper(Color.BLACK);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        
        
        a = new Polygon(løperX, løperY, løperX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);


        cur = new Point(1, 0);
        p = new Springer(Color.BLACK);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(springerX, springerY, springerX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);

        cur = new Point(6, 0);
        p = new Springer(Color.BLACK);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(springerX, springerY, springerX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(180), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);


        cur = new Point(1, 7);
        p = new Springer(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(springerX, springerY, springerX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);

        cur = new Point(6, 7);
        p = new Springer(Color.WHITE);

        curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
        curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

        a = new Polygon(springerX, springerY, springerX.length);
        transform = new AffineTransform();
        transform.translate(curX, curY);
        transform.rotate(Math.toRadians(0), a.getBounds().getX() + a.getBounds().width / 2, a.getBounds().getY() + a.getBounds().height / 2);

        a = transform.createTransformedShape(a);

        p.setGfx(a);
        board.get(cur).setBonde(p);
        p.setActive(true);
        allBondes.add(p);




        Player player1 = new Player();
        Player player2 = new Player();
        player1.setColor(Color.WHITE);
        player2.setColor(Color.BLACK);

        player1.setBondes(allBondes.stream().filter(Bonde -> Bonde.getColor() == Color.WHITE).collect(Collectors.toSet()));


        player2.setBondes(allBondes.stream().filter(Bonde -> Bonde.getColor() == Color.BLACK).collect(Collectors.toSet()));

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);


        Board newBoard = new Board();
        newBoard.setBoard(board);
        newBoard.setHeight(8);
        newBoard.setWidth(8);
        newBoard.setPieces(allBondes);

        Game newGame = new Game();
        newGame.setBoard(newBoard);
        newGame.setPlayers(players);
        return newGame;
        
        
        
    }
}
