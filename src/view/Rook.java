package view;

import javax.swing.ImageIcon;

public class Rook extends Piece {

	public Rook(boolean white) {
		this.white = white;
		this.str = (white) ? "R" : "r";
		
		String imgstr = "resources/" + ((white) ? "wrook.gif" : "brook.gif");
		this.img = new ImageIcon( getClass().getResource(imgstr) );
	}
	
	@Override
	public
	boolean validMove(int xfrom, int yfrom, int xto, int yto, boolean capture) {
		return isUpDownLeftRight(xfrom, yfrom, xto, yto);
	}
}
