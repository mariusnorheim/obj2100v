package model.stockfish.runtimestrategy;

import model.IConfiguration;
import model.Messagetype;

import java.io.BufferedReader;
import java.util.Observable;

public class StockfishProcess extends Observable implements IConfiguration {
	private static String processFilename = PROCESS_FILE_LOCATION_ON_THE_DESKTOP;
	private ClientThread client;
		
	public StockfishProcess(BufferedReader reader) {	
		client = new ClientThread(processFilename, this, reader);
		client.start();		
	}
	
	public void update(Messagetype message) {
		setChanged();
		notifyObservers(message);
		clearChanged();		
	}

}
