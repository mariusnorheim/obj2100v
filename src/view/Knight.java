package view;

import javax.swing.ImageIcon;

public class Knight extends Piece {

	public Knight(boolean white) {
		this.white = white;
		this.str = (white) ? "N" : "n";
		
		String imgstr = "resources/" + ((white) ? "wknight.gif" : "bknight.gif");
		this.img = new ImageIcon( Piece.class.getResource(imgstr) );
	}
	
	@Override
	public
	boolean validMove(int xfrom, int yfrom, int xto, int yto, boolean capture) {
		return ((Math.abs(xfrom - xto) == 1 && Math.abs(yfrom - yto) == 2) ||
				(Math.abs(yfrom - yto) == 1 && Math.abs(xfrom - xto) == 2));
	}
}