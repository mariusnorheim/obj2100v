package model.commandbuilderstrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import model.Messagetype;
import model.ModelStrategy;

public class ClientThread extends ModelStrategy
{
  private StockfishProcess stockfishProcess;
  
  public ClientThread(String processFilename, StockfishProcess stockfishProcess, BufferedReader br)
  {
    this.stockfishProcess = stockfishProcess;
    this.commandReader = br;
    ProcessBuilder pb = new ProcessBuilder(new String[] { processFilename });
    try
    {
      Process process = pb.start();
      this.processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      this.processWriter = new OutputStreamWriter(process.getOutputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  protected void talkToStockfish(String command)
  {
    try
    {
      this.processWriter.write(command + "\n");
      this.processWriter.flush();
      this.processWriter.write("isready\n");
      this.processWriter.flush();
      
      String l = "";
      String result = "";
      while (!(l = this.processReader.readLine()).equals("readyok")) {
        result = result + l + "\n";
      }
      this.stockfishProcess.update(new Messagetype("result", result));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}