package edu.ics499.fumeappapi.domain;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;


/**
 * @author marselos a. reed , qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 *
 */
public class Transaction implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private NodeList userList;
	private Calendar date;
	private String message, userName, pin, networkID, Connection, filePath;
	private File file;
	private Node node;
	private int port;
	private static Transaction transaction;

//	private Transaction() {
//		userList = NodeList.getInstance();
//	}
//
//	public static Transaction getInstance() {
//		try {
//			if (transaction == null) {
//
//				return transaction = new Transaction();
//			} else {
//				return transaction;
//			}
//		} catch (Exception e) {
//			return null;
//		}
//	}

    public String getDate() {
        return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/"
                + date.get(Calendar.YEAR);
    }
	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {this.date = date;}

	/**
	 * @param message the message to set
	 */

	/**
	 * @return the message
	 */
	public String getMessage() {return message;}
	/**
	 * @return the date
	 */

	public String setMessage(String message) {return this.message = message;}
	/**
	 * @return the file
	 */
	public File getFile() {return file;}
	/**
	 * @param file the file to set
	 */
	public File setFile(File file) {return this.file = file;}
	/**
	 * @return the pin
	 */
	public String getPin() {return pin;}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {this.pin = pin;}
	/**
	 * @return the networkID
	 */
	public String getNetworkID() {return networkID;}
	/**
	 * @param networkID the networkID to set
	 */
	public void setNetworkID(String networkID) {this.networkID = networkID;}
	/**
	 * @return the connection
	 */
	public String getConnection() {return Connection;}
	/**
	 * @param connection the connection to set
	 */
	public void setConnection(String connection) {Connection = connection;}
	/**
	 * @return the userName
	 */
	public String getUserName() {return userName;}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {this.userName = userName;}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {return filePath;}
	/**
	 * @param filePath the filePath to set
	 */
	public String setFilePath(String filePath) {return this.filePath = filePath;}
	/**
	 * @return the node
	 */
	public Node getNode() {return node;}
	/**
	 * @param node the node to set
	 */
	public void setNode(Node node) {this.node = node;}
	/**
	 * @return the port
	 */
	public int getPort() {return port;}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {this.port = port;}


}
