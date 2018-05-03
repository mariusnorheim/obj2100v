package view;

import javax.swing.ImageIcon;

public class Pawn extends Piece {
	
	public Pawn(boolean white) {
		this.white = white;
		this.str = (white) ? "P" : "p";
		
		String imgstr = "resources/" + ((white) ? "wpawn.gif" : "bpawn.gif");
		this.img = new ImageIcon( getClass().getResource(imgstr) );
	}
	
	@Override
	public
	boolean validMove(int xfrom, int yfrom, int xto, int yto, boolean capture) {
		return (this.validDirection(yfrom, yto) &&			// Pawns move only forward.
				((this.firstMove) ?							// Is this the pawn's first move?
					((Math.abs(yto - yfrom) == 2) ||		// Then it can move 2 spaces forward
						Math.abs(yto - yfrom) == 1) : 		// or one space
					(Math.abs(yto-yfrom) == 1)) &&			// Otherwise just one space
				((capture && 								// It's a capture,
					Math.abs(xto - xfrom) == 1 &&			// And the move is forward and diagonal by 1
					Math.abs(yto-yfrom) == 1) ||
					(!capture && xfrom == xto)));			// or it's not, and the move is only forward
	}

	private boolean validDirection(int yfrom, int yto) {
		return (this.white) ? (yfrom <= yto) : (yto <= yfrom);
	}
	
}
