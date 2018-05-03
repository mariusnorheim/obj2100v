package model;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Messagetype implements Serializable {
	private String mtype, msg;

	public String getMsg() {
		return msg;
	}
	
	public String getType() {
		return mtype;
	}

	public Messagetype(String mtype, ByteArrayOutputStream msg) {
		super();
		this.mtype = mtype;
		this.msg = msg.toString();
	}

	public Messagetype(String mtype, String msg) {
		super();
		this.mtype = mtype;
		this.msg = msg;
	}
}
