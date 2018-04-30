package model.socket;

import java.io.*;

public class SocketMessage implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String mtype;
  private String message;
  
  public String getMessage() {
    return this.message;
  }

  public String getType() {
    return this.mtype;
  }
  
  public SocketMessage(String mtype, ByteArrayOutputStream message)
  {
    this.mtype = mtype;
    this.message = message.toString();
  }
  
  public SocketMessage(String mtype, String message)
  {
    this.mtype = mtype;
    this.message = message;
  }
}