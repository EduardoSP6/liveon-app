package com.example.liveon_app.network.responses;

public class AuthResponse {

    private Boolean success;
    private String token;
    private String avatar_url;
    private String email;
    private String username;
    private String fullname;
    private String city;
    private String state_abbr;

    public AuthResponse(Boolean success, String token, String avatar_url, String email, String username, String fullname, String city, String state_abbr) {
        this.success = success;
        this.token = token;
        this.avatar_url = avatar_url;
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.city = city;
        this.state_abbr = state_abbr;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_abbr() {
        return state_abbr;
    }

    public void setState_abbr(String state_abbr) {
        this.state_abbr = state_abbr;
    }
}
