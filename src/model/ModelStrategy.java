package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public abstract class ModelStrategy extends Thread
{
  protected BufferedReader commandReader;
  protected BufferedReader processReader;
  protected OutputStreamWriter processWriter;
  int count = 0;
  
  public void run()
  {
    try
    {
      String command;
      while ((command = this.commandReader.readLine()) != null)
      {
        if (!command.isEmpty()) {
          talkToStockfish(command);
        } else {
          System.out.println("command was empty, was not sent to stockfish");
        }
        System.out.println("Command #" + ++this.count + " [" + command + "] length " + command.length());
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  protected abstract void talkToStockfish(String paramString);
}
