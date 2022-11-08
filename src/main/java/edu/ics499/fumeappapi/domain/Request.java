/**
 * 
 */
package edu.ics499.fume.facade;

import java.util.Calendar;

/**
 * @author marselos a. reed , qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 *
 */
public class Request extends Transmit {
	private static Request request;
	private Calendar date;

	private Request() {}

	public static Request getInstance() {
		if(request == null) {
			request = new Request();
		}
		return request;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	
	
	

}
