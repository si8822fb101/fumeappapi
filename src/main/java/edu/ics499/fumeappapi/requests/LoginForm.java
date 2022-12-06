package edu.ics499.fumeappapi.requests;

import javax.validation.constraints.Size;

public class LoginForm {
    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    private String username;

    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    private String pin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
