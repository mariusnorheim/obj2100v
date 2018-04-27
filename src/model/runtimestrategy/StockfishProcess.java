package model.runtimestrategy;

import java.io.BufferedReader;
import java.util.Observable;
import model.IConfiguration;
import model.Messagetype;

public class StockfishProcess extends Observable implements IConfiguration
{
  private static String processFilename = "C:/Marius/workspace/repos/obj2100v/stockfish/Windows/stockfish_9_x32.exe";
  private ClientThread client;
  
  public StockfishProcess(BufferedReader reader)
  {
    this.client = new ClientThread(processFilename, this, reader);
    this.client.start();
  }
  
  public void update(Messagetype message)
  {
    setChanged();
    notifyObservers(message);
    clearChanged();
  }
}