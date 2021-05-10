package com.example.subastas.dto.users.http.request;

public class UpdatePasswordRequest {

    private String username;
    private String validationCode;
    private String password;

    public UpdatePasswordRequest(String username, String validationCode, String password) {
        this.username = username;
        this.validationCode = validationCode;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
