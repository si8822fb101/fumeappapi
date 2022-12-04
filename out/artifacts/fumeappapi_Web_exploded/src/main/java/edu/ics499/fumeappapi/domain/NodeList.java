package edu.ics499.fumeappapi.domain;

/**
 * 
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import edu.ics499.fumeappapi.domain.Message;
import edu.ics499.fumeappapi.domain.Node;
import edu.ics499.fumeappapi.domain.User;
import edu.ics499.fumeappapi.domain.Hash;




/**
 * @authors Marselos A. Reed, Qaalib Farah, John Quinlan, Ayden Sinn, Mohamed A. Mohamoud 
 *
 */
public class NodeList{
	private static NodeList userList;
	private List <User> ledger = new ArrayList<User>();
	private List<String> blocks = new ArrayList<String>();
	private Node head;
	private Message message;
	private int count;
	/**
	 * @param -  list
	 * @param -  capacity
	 */
	


	public static NodeList getInstance() {
		try {
			if (userList == null) {
				return userList = new NodeList();
			}
		} catch (Exception e) {
			return null;
		}
		return userList;
	}
	
	public NodeList() {
		ledger = new ArrayList<>();
	}

}
