package model;

public interface IConfiguration {
	// create or edit according to your location of the process file
	String PROCESS_FILE_LOCATION_ON_THE_YOGA = System.getProperty("user.dir") + "\\stockfish\\Windows\\stockfish_9_x32.exe";
	String PROCESS_FILE_LOCATION_ON_THE_DESKTOP = System.getProperty("user.dir") + "\\stockfish\\Windows\\stockfish_9_x64.exe";
		
	// better see the proper documentation for the uci protocol
	String FEN_EXAMPLE = "8/6pk/8/1R5p/3K3P/8/6r1/8 b - - 0 42"; 
	
}
