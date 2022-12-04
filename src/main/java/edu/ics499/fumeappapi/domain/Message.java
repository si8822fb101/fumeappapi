package edu.ics499.fumeappapi.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.Calendar;

public class Message  {
	private static String connection = "";
	private static int port;
	private static Calendar timeStamp;
	
	
	
	/**
	 * @return the connection
	 */
	public String getConnection() {return connection;}
	/**
	 * @param connection the connection to set
	 */
	public void setConnection(String connection) {this.setConnection(connection);;}

	/**
	 * @return the port
	 */
	public int getPort() {return port;}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {this.setPort(port);};

	public Calendar getTimeStamp() {return timeStamp;};

	public void  setTimeStamp(Calendar timeStamp) {this.setTimeStamp(Calendar.getInstance());};

	public Message(String connection, int port){
		connection = this.connection;
		port = this.port;
		timeStamp = Calendar.getInstance();
	}


}
