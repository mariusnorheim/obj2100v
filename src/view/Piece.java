package view;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class Piece {
	// A piece is white or black; White moves first
	public boolean white;
	public String str;
	public ImageIcon img;
	
	// Set to false after a piece's first move
	public boolean firstMove = true;
	
	public abstract boolean validMove(int xfrom, int yfrom, int xto, int yto, boolean capture);
	
	static boolean isDiagonal(int xfrom, int yfrom, int xto, int yto) {
		// A move is diagonal if abs(dx) == abs(dy)
		return (Math.abs(xto - xfrom) == Math.abs(yto - yfrom));
	}
	
	static boolean isUpDownLeftRight(int xfrom, int yfrom, int xto, int yto) {
		// Either only the y changes, or only the x changes.
		return ((xfrom == xto && yfrom != yto) ||
				(yfrom == yto && xfrom != xto));
	}
	
	public void paint(Graphics g, int x, int y) {
		g.drawImage(this.img.getImage(), x, y, null);
	}
}
