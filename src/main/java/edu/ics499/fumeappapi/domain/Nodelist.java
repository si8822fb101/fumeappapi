package edu.ics499.fumeappapi.domain;

/**
 * 
 */
package edu.ics499.fume.collections;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ics499.fume.entities.Message;
import edu.ics499.fume.entities.Node;
import edu.ics499.fume.entities.User;
import edu.ics499.fume.hash.Hash;




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
		this.setCount(0);
	
	}
	
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	public Iterator<User> iterator(){
		return ledger.iterator();
	}

	/** 
	 * 
	 * @param dataBlock
	 * Node - is added to the chain
	 * @return 
	 */
	
	public boolean insert(Node device) {
		if(device != null) {
			ledger.add((User) device);
			device = head;
			setCount(getCount() + 1);
		}
		return(ledger.add((User) device));
	}
	
	/**
	 * 
	 * @param dataBlock
	 * Node - is removed from the chain
	 * @return 
	 */
	
	public boolean remove(Node device) {
		for(int i = 0; i <= ledger.size(); i++) {
			if(ledger.contains(device)) {
				ledger.remove(device);
				setCount(getCount() - 1);
				return ledger.remove(device);
			}
		}
		return false;
	}
	
	public boolean searchId(String userId) {
		for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getUserName().equals(userId)) return true;
		}
		return false;
	}
	

	
	public void p2pMessaging(String destination, int portValue) {
		for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getIpAddress().equals(destination)) {
				message.setConnection(destination);
				message.setPort(portValue);
				message.messaging();
			}
		}
	}
	
	public void p2pFileTransfer(String destination) throws Exception {
		for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getIpAddress().equals(destination)) {
				
			}
		}
	}
	
	public void consensus(String destination, String data) {
		double threshold = 0.95;
		double confirm = 0;
		boolean sentinel = false;
		for(Iterator<String> iterator = blocks.iterator(); iterator.hasNext();) {
			String block = iterator.next();
			if(block.matches(data)) sentinel = true;
			double sentinelCount = sentinel ? 1 : 0;

			if(sentinelCount == 1) confirm = confirm + 1;
			double yield = (confirm / blocks.size());

			if(yield >= threshold) addToChain(convertToBlock(data));

		}

	}

	private String convertToBlock(String data) {
		return Hash.hashCreation(data);
	}
	
	private void addToChain(String block) {
		 blocks.add(block);
	}
	

	
	public void wipeNodes() {
		ledger.clear();
	}

	
}
