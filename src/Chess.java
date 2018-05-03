import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import view.*;

public class Chess {
	
	// Environment variables
	public static Piece[][] board;
	public static BoardComponent bc;
	public static final int PIECE_XY = 44;
	public static final int WIDTH = PIECE_XY*8, HEIGHT = PIECE_XY*8 + 22; 
	
	// Game mechanic variables
	private static boolean whiteTurn;
	private static boolean testing;
	
	public static void main(String[] args) {
		// Construct chess board
		board = new Piece[8][8];
		buildBoard(board);
		whiteTurn = true;
		
		// Initialize the frame
		JFrame window = new JFrame();
		window.setSize(WIDTH, HEIGHT);
		window.setTitle("Chess");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize the component, add mouse listener
		bc = new BoardComponent();
		bc.addMouseListener(genBoardMouseListener());
		bc.setBounds(0, 0, WIDTH, HEIGHT);
		window.add(bc);
		
		// LAST
		window.setVisible(true);
	}
	
	public static void rawMove(int xfrom, int yfrom, int xto, int yto) {
		// Differs from move in that it accepts raw x y coordinates, and
		// attempts to call move.

		int xf = (int) Math.floor(xfrom / PIECE_XY);
		int yf = (int) Math.floor(yfrom / PIECE_XY);
		int xt = (int) Math.floor(xto / PIECE_XY);
		int yt = (int) Math.floor(yto / PIECE_XY);
		
		move(board, xf, yf, xt, yt);
	}
	
	// Accepts a board and board position coordinates	
	private static boolean move(Piece[][] board, int xfrom, int yfrom, int xto, int yto) {

		Piece from = board[yfrom][xfrom];
		Piece to = board[yto][xto];
		boolean capture = (to != null);
		
		if (castleMove(board, capture, xfrom, yfrom, xto, yto)) {
			int kDest, rDest;
			boolean fromKing;
			if (from instanceof King) {
				fromKing = true;
				kDest = (xto == 7) ? 6 : 2;
				rDest = (xto == 7) ? 5 : 3;
			} else {
				fromKing = false;
				kDest = (xfrom == 7) ? 6 : 2;
				rDest = (xfrom == 7) ? 5 : 3;
			}
			
			board[yfrom][xfrom] = null;
			board[yto][xto] = null;
			
			board[yfrom][kDest] = (fromKing) ? from : to;
			board[yto][rDest] = (fromKing) ? to : from;
			
			endTurn(board, from, to, true);
			return true;
			
		} else if (standardMove(board, capture, xfrom, yfrom, xto, yto)) {
			if (from instanceof Pawn && yto == ((from.white) ? 7 : 0)) {
				board[yfrom][xfrom] = null;
				board[yto][xto] = new Queen(from.white);
			} else {
				board[yfrom][xfrom] = null;
				board[yto][xto] = from;
			}
			
			endTurn(board, from, to, false);
			return true;
			
		} else {
			return false;
		}
	}
	
	private static void endTurn(Piece[][] board, Piece from, Piece to, boolean castle) {
		if (to != null)
			to.firstMove = (castle) ? false : to.firstMove;
		from.firstMove = false;
		whiteTurn = !whiteTurn;
		
		bc.repaint();
	}

	private static boolean standardMove(Piece[][] board, boolean capture, int xfrom, int yfrom, int xto, int yto) {
		// Validates a standard move given a slew of input.
		// A standard move follows the general rules of chess, as opposed to fleeing check
		// or castling a King and Rook, which require alternate validations.
		Piece from = board[yfrom][xfrom];
		Piece to = board[yto][xto];

		return (from != null &&											// from isn't null
				correctTurn(from.white) &&								// correct player's turn
				from.validMove(xfrom, yfrom, xto, yto, capture) && 		// valid move for that piece
				(!capture || from.white != to.white) && 				// moving to valid position
				notBlocked(board, xfrom, yfrom, xto, yto) &&			// the piece is not blocked
				kingAvoidsCheck(board, from, xfrom, yfrom, xto, yto));
	}
	
	private static boolean castleMove(Piece[][] board, boolean capture, int xfrom, int yfrom, int xto, int yto) {
		Piece from = board[yfrom][xfrom];
		Piece to = board[yto][xto];
		
		return (from != null && to != null &&		
				correctTurn(from.white) &&								// A valid castle move:
				from.white == to.white &&								// From and to are same color,
				(from instanceof King && to instanceof Rook || 			// a king and rook are switching,
						from instanceof Rook && to instanceof King) &&
						from.firstMove && to.firstMove &&						// it is both pieces' first move,
						notBlocked(board, xfrom, yfrom, xto, yto));				// and there is nothing blocking them.
	}
	
	private static boolean correctTurn(boolean white) {
		// Testing flag turns off correctTurn validations
		return (testing) ? true : white == whiteTurn;
	}
	
	private static boolean kingAvoidsCheck(Piece[][] board, Piece piece, int xfrom, int yfrom, int xto, int yto) {
		// Returns true if the given piece could move to board[yto][xto] without
		// encountering check.
		Piece[][] team = getTeam(board, piece.white);
		int[] king = getKing(team);
		
		boolean ret = true;
		
		Piece toOrig = board[yto][xto];
		board[yto][xto] = piece;
		board[yfrom][xfrom] = null;
		
		if (piece instanceof King) {
			king[0] = yto;
			king[1] = xto;
		}
		
		// Declare opponents after the board is temporarily modified
		Piece[][] opps = getTeam(board, !piece.white);
		
		for (int i = 0; i < opps.length; i++) {
			for (int j = 0; j < opps.length; j++) {
				Piece opp = opps[i][j];
				
				if (opp != null && 
					opp.validMove(j, i, king[1], king[0], true) &&
					notBlocked(board, j, i, king[1], king[0])) {
					ret = false;
				}
			}
		}
		
		board[yfrom][xfrom] = piece;
		board[yto][xto] = toOrig;
		
		return ret;
	}
	
	private static int[] getKing(Piece[][] team) {
		// Returns the King on the given team.
		// Expects a single team, not the entire board.
		// Returns null if a King can't be found.
		
		for (int i = 0; i < team.length; i++) {
			for (int j = 0; j < team.length; j++) {
				Piece piece = team[i][j];
				
				if (piece != null && piece instanceof King) {
					return new int[] {i, j};
				}
			}
		}
		
		for (Piece[] row : team) {
			for (Piece piece : row) {
				if (piece != null && piece instanceof King) {
				}
			}
		}
		return null;
	}
	
	private static Piece[][] getTeam(Piece[][] board, boolean white) {
		// Returns the opponents of the given color in their current positions
		Piece[][] team = new Piece[8][8];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				Piece piece = board[i][j];
				team[i][j] = (piece != null && piece.white == white) ? piece : null;
			}
		}
		
		return team;
	}
	
	private static boolean notBlocked(Piece[][] board, int xfrom, int yfrom, int xto, int yto) {
		// Returns true if the entire path from the origin to the destination is clear.
		// (This excepts knights, which can move over teammates and enemies.)
		
		Piece from = board[yfrom][xfrom];
		Piece to = board[yto][xto];
		
		// Determine the direction (if any) of x and y movement
		int dx = (xfrom < xto) ? 1 : ((xfrom == xto) ? 0 : -1);
		int dy = (yfrom < yto) ? 1 : ((yfrom == yto) ? 0 : -1);
		
		// Determine the number of times we must iterate
		int steps = Math.max(Math.abs(xfrom - xto), Math.abs(yfrom - yto));
		
		if (xfrom == xto || yfrom == yto || Math.abs(xfrom - xto) == Math.abs(yfrom - yto)) {
			for (int i = 1; i < steps; i++) {
				int x = xfrom + i * dx;
				int y = yfrom + i * dy;
				if (isBlocked(board, from, to, x, y)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean isBlocked(Piece[][] board, Piece from, Piece to, int x, int y) {
		return (board[y][x] != null && board[y][x] != to && board[y][x] != from);
	}
	
	private static MouseListener genBoardMouseListener() {
		return new MouseListener() {
			int xfrom;
			int yfrom;
			
			@Override
			public void mousePressed(MouseEvent e) {
				// Overwrite previous x and y from values
				this.xfrom = e.getX();
				this.yfrom = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Pass mouse from and to coordinates to Chess to handle
				// validation and moves
				int xto = e.getX();
				int yto = e.getY();
				int xfrom = this.xfrom;
				int yfrom = this.yfrom;
				
				Chess.rawMove(xfrom, yfrom, xto, yto);
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		};
	}

	private static void buildBoard(Piece[][] board) {
		// Rows 0 and 1 will contain white pieces,
		// and rows 6 and 7 will contain black pieces.
		
		// Place pawns in rows 1 and 6
		for (int i = 0; i < board.length; i++) {
			board[1][i] = new Pawn(true);
			board[6][i] = new Pawn(false);
		}
		
		// Place white and black row pieces.
		placeBackRow(board, true, 0);
		placeBackRow(board, false, 7);
	}
	
	private static void placeBackRow(Piece[][] board, boolean white, int row) {
		// Place rooks on the outside
		board[row][0] = new Rook(white);
		board[row][7] = new Rook(white);
		
		// Place knights inside of rooks
		board[row][1] = new Knight(white);
		board[row][6] = new Knight(white);
		
		// Place bishops inside of knights
		board[row][2] = new Bishop(white);
		board[row][5] = new Bishop(white);
		
		// Place king and queen
		board[row][3] = new Queen(white);
		board[row][4] = new King(white);
	}
	
}
