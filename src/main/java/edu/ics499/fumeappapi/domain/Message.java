package edu.ics499.fumeappapi.domain;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.Calendar;

public class Message  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fromUsername;
	private String toUsername;
	private String content;
	private Calendar timeStamp;

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Calendar timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Message(String fromUsername, String toUsername, String content){
		this.fromUsername = fromUsername;
		this.toUsername = toUsername;
		this.content = content;
		this.timeStamp = Calendar.getInstance();
	}


}
