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

        Set<Spillebrikke> alleSpillebrikker = new HashSet<>();

        Spillebrikke p = new Konge(Color.WHITE);
        Point cur = new Point(0, 4);
        AffineTransform transform = new AffineTransform();
        
        String [] SpillbrikkeFarge = {"svart","hvit"};
        
        // --------- TEGN KONGER ------------
        int[] KONGEX = {10, 20, 22, 22, 24, 25, 25, 24, 22, 20, 18, 17, 15,
                13, 12, 10, 8, 6, 5, 5, 6, 8, 8, 10};
        int[] KONGEY = {5, 5, 6, 10, 13, 15, 17, 18, 20, 20, 18, 22, 23,
                22, 18, 20, 20, 18, 17, 15, 13, 10, 6, 5}; 
           
        for(String farge : SpillbrikkeFarge) {
        	int angle = 0;
        	
        	if (farge == "hvit") {
        		cur = new Point(4, 7);
            	p = new Konge(Color.WHITE);
            	angle = 180;
            } else if(farge == "svart") {
            	cur = new Point(4, 0);
            	p = new Konge(Color.BLACK);
            	angle = 0;
            }
        	
        	int curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
            int curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

            Shape konge = new Polygon(KONGEX, KONGEY, KONGEX.length);
            transform = new AffineTransform();
            transform.translate(-konge.getBounds().x, -konge.getBounds().y);
            transform.translate(curX, curY);
            transform.rotate(Math.toRadians(angle), 
            		konge.getBounds().getX() 
            		+ konge.getBounds().width / 2,
            		konge.getBounds().getY() 
            		+ konge.getBounds().height / 2);

            konge = transform.createTransformedShape(konge);
            
            p.setGfx(konge);
            board.get(cur).setSpillebrikke(p);
            p.setActive(true);
            alleSpillebrikker.add(p);        
        }
        
        // --------- TEGN DRONNINGER ------------
        int dronningX[]={ 5, 8, 8, 5, 5, 9,13,15,17,21,25,25,22,22,25};
        int dronningY[]={30,24,17,11, 3, 6, 5, 2, 5, 6, 3,11,17,24,30};
 
        for(String farge : SpillbrikkeFarge) {
        	int angle = 0;
        	
        	if (farge == "hvit") {
        		cur = new Point(3, 7);
            	p = new Dronning(Color.WHITE);
            	angle = 0;
            } else if(farge == "svart") {
            	cur = new Point(3, 0);
            	p = new Dronning(Color.BLACK);
            	angle = 180;
            }
        	
        	int curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
            int curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;

            Shape dronning = new Polygon(dronningX, dronningY, dronningX.length);
            transform = new AffineTransform();
            transform.translate(curX, curY);
            transform.rotate(Math.toRadians(angle), 
            		dronning.getBounds().getX() 
            		+ dronning.getBounds().width / 2, 
            		dronning.getBounds().getY() 
            		+ dronning.getBounds().height / 2);

            dronning = transform.createTransformedShape(dronning);

            p.setGfx(dronning);
            board.get(cur).setSpillebrikke(p);
            p.setActive(true);
            alleSpillebrikker.add(p);        
        }
        
        String [] BrettSide = {"L","R"};
        
        // --------- TEGN SPRINGERE ------------
        int springerX[]={ 5, 8, 8, 8,12, 6, 5,14,15,16,17,18,21,24,25,22,22,25};
        int springerY[]={30,24,17,17,13,14,11, 5, 3, 5, 3, 5, 6, 8,11,17,24,30};
        
        for(String farge : SpillbrikkeFarge) {
        	int angle = 0;
        	for(String side : BrettSide) {        		
        		if (side == "L") {
        			if (farge == "hvit") {
                		cur = new Point(1, 7);
                    	p = new Springer(Color.WHITE);
                    	angle = 0;
                    } else if(farge == "svart") {
                    	cur = new Point(1, 0);
                    	p = new Springer(Color.BLACK);
                    	angle = 180;
                    }
        		} else if (side == "R") {
        			if (farge == "hvit") {
                		cur = new Point(6, 7);
                    	p = new Springer(Color.WHITE);
                    	angle = 0;
                    } else if(farge == "svart") {
                    	cur = new Point(6, 0);
                    	p = new Springer(Color.BLACK);
                    	angle = 180;
                    }
        		}
        		
        		int curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
                int curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
                
                Shape springer = new Polygon(springerX, springerY, springerX.length);
                transform = new AffineTransform();
                transform.translate(curX, curY);
                transform.rotate(Math.toRadians(angle), 
                		springer.getBounds().getX() 
                		+ springer.getBounds().width / 2, 
                		springer.getBounds().getY() 
                		+ springer.getBounds().height / 2);

                springer = transform.createTransformedShape(springer);

                p.setGfx(springer);
                board.get(cur).setSpillebrikke(p);
                p.setActive(true);
                alleSpillebrikker.add(p);	
        	}
        }
        
        // --------- TEGN LØPERE/HESTER ------------
        int løperX[]={ 5, 8, 8, 5, 6, 9,10,12,15,13,17,21,24,25,22,22,25};
        int løperY[]={30,24,17,11, 8, 6, 6,11,10, 5, 5, 6, 8,11,17,24,30};
          
        for(String farge : SpillbrikkeFarge) {
        	int angle = 0;
        	for(String side : BrettSide) {        		
        		if (side == "L") {
        			if (farge == "hvit") {
                		cur = new Point(2, 7);
                    	p = new Løper(Color.WHITE);
                    	angle = 0;
                    } else if(farge == "svart") {
                    	cur = new Point(2, 0);
                    	p = new Løper(Color.BLACK);
                    	angle = 180;
                    }
        		} else if (side == "R") {
        			if (farge == "hvit") {
                		cur = new Point(5, 7);
                    	p = new Løper(Color.WHITE);
                    	angle = 0;
                    } else if(farge == "svart") {
                    	cur = new Point(5, 0);
                    	p = new Løper(Color.BLACK);
                    	angle = 180;
                    }
        		}
        		
        		int curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
                int curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
                
                Shape løper = new Polygon(løperX, løperY, løperX.length);
                transform = new AffineTransform();
                transform.translate(curX, curY);
                transform.rotate(Math.toRadians(angle), 
                		løper.getBounds().getX() 
                		+ løper.getBounds().width / 2,
                		løper.getBounds().getY() 
                		+ løper.getBounds().height / 2);

                løper = transform.createTransformedShape(løper);

                p.setGfx(løper);
                board.get(cur).setSpillebrikke(p);
                p.setActive(true);
                alleSpillebrikker.add(p);	
        	}
        }
        
        // --------- TEGN TÅRN ------------
        int tårnX[]={ 5, 8, 8, 5, 5, 9, 9,13,13,17,17,21,21,25,25,22,22,25};
        int tårnY[]={30,24,17,11, 6, 6, 9, 9, 6, 6, 9, 9, 6, 6,11,17,24,30};
          
        for(String farge : SpillbrikkeFarge) {
        	int angle = 0;
        	for(String side : BrettSide) {        		
        		if (side == "L") {
        			if (farge == "hvit") {
                		cur = new Point(0, 7);
                    	p = new Tårn(Color.WHITE);
                    	angle = 0;
                    } else if(farge == "svart") {
                    	cur = new Point(0, 0);
                    	p = new Tårn(Color.BLACK);
                    	angle = 180;
                    }
        		} else if (side == "R") {
        			if (farge == "hvit") {
                		cur = new Point(7, 7);
                    	p = new Tårn(Color.WHITE);
                    	angle = 0;
                    } else if(farge == "svart") {
                    	cur = new Point(7, 0);
                    	p = new Tårn(Color.BLACK);
                    	angle = 180;
                    }
        		}
        		
        		int curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
                int curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
                
                Shape tårn = new Polygon(tårnX, tårnY, tårnX.length);
                transform = new AffineTransform();
                transform.translate(curX, curY);
                transform.rotate(Math.toRadians(angle), 
                		tårn.getBounds().getX() 
                		+ tårn.getBounds().width / 2,
                		tårn.getBounds().getY() 
                		+ tårn.getBounds().height / 2);

                tårn = transform.createTransformedShape(tårn);

                p.setGfx(tårn);
                board.get(cur).setSpillebrikke(p);
                p.setActive(true);
                alleSpillebrikker.add(p);	
        	}
        }
        
        // --------- TEGN BØNDER ------------
        int bondeX[]={ 5,11,10,11,12,15,18,19,20,19,25};
        int bondeY[]={30,18,15,12,11,10,11,12,15,18,30};
        for(int i = 0 ; i < 8; i++){  
	        for(String farge : SpillbrikkeFarge) {
	        	int angle = 0;      		
	        	if (farge == "hvit") {
	        		cur = new Point(i, 6);
	        		p = new Bonde(Color.WHITE);
	        		angle = 0;
	            } else if(farge == "svart") {
	            	cur = new Point(i, 1);
	            	p = new Bonde(Color.BLACK);
	            	angle = 180;
	            }
	        		
	        	int curX = cur.x * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
	            int curY = cur.y * GamePanel.BOXSIZE + GamePanel.BOXSIZE / 3;
	                
	            Shape bonde = new Polygon(bondeX, bondeY, bondeX.length);
	            transform = new AffineTransform();
	            transform.translate(curX, curY);
	            transform.rotate(Math.toRadians(angle), 
	                	bonde.getBounds().getX() 
	                	+ bonde.getBounds().width / 2, 
	                	bonde.getBounds().getY() + 
	                	bonde.getBounds().height / 2);
	
	            bonde = transform.createTransformedShape(bonde);
	
	            p.setGfx(bonde);
	            board.get(cur).setSpillebrikke(p);
	            p.setActive(true);
	            alleSpillebrikker.add(p);	
	        }
        }

        Player player1 = new Player();
        Player player2 = new Player();
        player1.setColor(Color.WHITE);
        player2.setColor(Color.BLACK);

        player1.setSpillebrikke(
        		alleSpillebrikker.stream().filter(Spillebrikke -> Spillebrikke.getColor() 
        		== Color.WHITE).collect(Collectors.toSet())
        		);


        player2.setSpillebrikke(
        		alleSpillebrikker.stream().filter(Spillebrikke -> Spillebrikke.getColor() 
        		== Color.BLACK).collect(Collectors.toSet())
        		);

 
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);


        Board newBoard = new Board();
        newBoard.setBoard(board);
        newBoard.setHeight(8);
        newBoard.setWidth(8);
        newBoard.setPieces(alleSpillebrikker);

        Game newGame = new Game();
        newGame.setBoard(newBoard);
        newGame.setPlayers(players);
        return newGame;
        
     
        
    }
}
