package edu.ics499.fumeappapi.domain;

import java.util.Calendar;

public class Transfer {
    private static final long serialVersionUID = 1L;
    private NodeList userList;
    private Calendar date;
    private String message, userName, pin, networkID, Connection, filePath;
    private FileTransfer file;
    private Node node;
    private int port;

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
    public FileTransfer getFile() {return file;}
    /**
     * @param file the file to set
     */
    public FileTransfer setFile(FileTransfer file) {return this.file = file;}
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
