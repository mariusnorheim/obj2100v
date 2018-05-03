package view;

import javax.swing.ImageIcon;

public class Bishop extends Piece {

	public Bishop(boolean white) {
		this.white = white;
		this.str = (white) ? "B" : "b";
		
		String imgstr = "resources/" + ((white) ? "wbishop.gif" : "bbishop.gif");
		this.img = new ImageIcon( Piece.class.getResource(imgstr) );
	}
	
	@Override
	public
	boolean validMove(int xfrom, int yfrom, int xto, int yto, boolean capture) {
		return isDiagonal(xfrom, yfrom, xto, yto);
	}
}
