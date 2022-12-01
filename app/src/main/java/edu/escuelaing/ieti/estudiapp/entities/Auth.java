package edu.escuelaing.ieti.estudiapp.entities;

public class Auth {

    String Token;
    String expirationDate;

    public Auth(){

    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
