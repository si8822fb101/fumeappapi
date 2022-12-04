package edu.ics499.fumeappapi.domain;

/**
 * 
 */

import java.io.IOException;
import java.net.*;
import java.util.Calendar;

/**
 * @author marselos a. reed, qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 * 
 * USER- Represents a user profile
 */
public class User extends Node{
	private String userName, pin;
	private Calendar userCreateDate;
	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User(String userName, String pin) throws IOException {
		super();
		this.userName = userName;
		this.setPin(pin);
		this.userCreateDate = Calendar.getInstance();
	}
	
	/**
	 * @return - the userName
	 */
	public String getUserName() {return userName;}


	/**
	 * @param userName - the userName to set
	 */
	public void setUserName(String userName) {this.userName = userName;}


	/**
	 * @return -  the pin
	 */
	public String getPin() {return pin;}


	/**
	 * @param pin - the pin to set
	 */
	public void setPin(String pin) {this.pin = pin;}

	/**
	 * @return the userCreationDate
	 */
	public Calendar getUserCreateDate() {return userCreateDate;}

	/**
	 * @param userCreationDate the userCreationDate to set
	 */
	public void setUserCreateDate(Calendar userCreationDate) {this.userCreateDate = userCreationDate;}
	
	/**
	 * 
	 * @param - userName
	 * @return - boolean 
	 */
	public boolean match(String userName) {
		return this.getUserName().equalsIgnoreCase(userName);
	}

	public boolean equals(Object userObject) {
		if (this == userObject) return true;
		if(userObject == null || getClass() != userObject.getClass()) return false;
		User user = (User) userObject;
		return getUserName().equalsIgnoreCase(user.getUserName());
	}

}
