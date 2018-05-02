package controller;

public class PointToFen {	
	
	public String setPointToFen(int x, int y) {
		
    	String fenX = "";
    	
		switch (x) {
			case 0:
				fenX = "a";
				break;
			case 1:
				fenX = "b";
				break;
			case 2:
				fenX = "c";
				break;
			case 3:
				fenX = "d";
				break;
			case 4:
				fenX = "e";
				break;
			case 5:
				fenX = "f";
				break;
			case 6:
				fenX = "g";
				break;
			case 7:
				fenX = "h";
				break;
			default:
				fenX = "";					
		}
		
		String fenY = "";
		switch (y) {
			case 0:
				fenY = "8";
				break;
			case 1:
				fenY = "7";
				break;
			case 2:
				fenY = "6";
				break;
			case 3:
				fenY = "5";
				break;
			case 4:
				fenY = "4";
				break;
			case 5:
				fenY = "3";
				break;
			case 6:
				fenY = "2";
				break;
			case 7:
				fenY = "1";
				break;
			default:
				fenY = "";					
		}
		
		String fen = fenX + fenY;
		System.out.print(fen);
		
		return fen;
		
	}
	
}
