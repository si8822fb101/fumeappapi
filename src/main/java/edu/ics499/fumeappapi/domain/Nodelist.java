package edu.ics499.fumeappapi.domain;

public class Nodelist {
    private List <User> ledger = new ArrayList<User>();
    private List<String> blocks = new ArrayList<String>();
    private Node head;
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

}
