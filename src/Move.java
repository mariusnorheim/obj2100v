public class Move extends Chess {
	int x, y, xto, yto;
	Double score;
	
	public Move(int x, int y, int xto, int yto) {
		this.x = x;
		this.y = y;
		this.xto = xto;
		this.yto = yto;
	}

}
