/**
 * 
 */
package edu.ics499.fume.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import edu.ics499.fume.entities.User;

/**
 * @author marselos a. reed, qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 *
 */
public class UserIterator implements Iterator<User> {
	private User user; 
	private Predicate<User> predicate;
	private Iterator<User> iterator;
	
	
	public UserIterator(Iterator<User> iterator, Predicate<User> predicate) {
		this.predicate = predicate;
		this.iterator = iterator;
		getNextItem();
	}


	
	public boolean hasNext() {
		return user != null;
	}
	
	public User next() {
		if(!hasNext()) {
			throw new NoSuchElementException("No such element");
		}
		User returnValue = user;
		getNextItem();
		return returnValue;
		
	}
	
	private void getNextItem() {
		while(iterator.hasNext()) {
			user = iterator.next();
			if(predicate.test(user))
				return;
			
		}
		user = null;
	}



}
