package edu.ics499.fumeappapi.domain;

/**
 * @author marselos a. reed, qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 *
 * USER- Represents a user profile
 */
public class User{
    private String userName = "", pin;

    public User(String userName, String pin) {
        this.userName = userName;
        this.setPin(pin);

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

}
