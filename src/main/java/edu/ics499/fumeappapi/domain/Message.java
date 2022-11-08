package edu.ics499.fumeappapi.domain;
/**
 * 
 */
package edu.ics499.fume.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author marselos a. reed, qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 * 
 *
 */
public class Message  {
	private String connection = "";
	private int port;
	
	
	
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
	public void setPort(int port) {this.setPort(port);;}



	public void messaging() {
		try (Socket echo = new Socket(connection, port);
				PrintWriter send = new PrintWriter(echo.getOutputStream(), true);
				BufferedReader receive = new BufferedReader(new InputStreamReader(echo.getInputStream()));
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in))
				){
				String message;
				while((message = input.readLine()) != null) {
					send.println(message);
					System.out.println(receive.readLine());			}
			} catch (UnknownHostException e) {
				System.err.println();
			} catch (IOException e) {
				System.err.println();
			}
			
			
	}


}
