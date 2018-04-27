package model;

import java.io.ByteArrayOutputStream;

public class Messagetype
{
  private String mtype;
  private String message;
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getType()
  {
    return this.mtype;
  }
  
  public Messagetype(String mtype, ByteArrayOutputStream message)
  {
    this.mtype = mtype;
    this.message = message.toString();
  }
  
  public Messagetype(String mtype, String msg)
  {
    this.mtype = mtype;
    this.message = msg;
  }
}