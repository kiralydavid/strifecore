package com.strifecore.core.security;

public class AuthenticationDto {

    private String name;
    private String token;
    private Boolean admin;

    public AuthenticationDto() {
    }

    public AuthenticationDto(String name, String token, Boolean admin) {
        this.name = name;
        this.token = token;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
