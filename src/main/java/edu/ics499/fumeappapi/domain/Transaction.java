package edu.ics499.fumeappapi.domain;

import edu.ics499.fumeappapi.services.TransactionService;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


/**
 * @author marselos a. reed , qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 *
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Calendar date;
	private String from, to, pin;
	private Message message;
	private File file;

	public Transaction(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		this.setDate(calendar);
	}

    public String getDate() {
        return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/"
                + date.get(Calendar.YEAR);
    }
	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {this.date = date;}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return Objects.equals(date, that.date) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(pin, that.pin) && Objects.equals(message, that.message) && Objects.equals(file, that.file);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, from, to, pin, message, file);
	}
}
