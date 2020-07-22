package com.bf.dt.entity;

public class LoginToken {
    private String id;
    private String login_name;
    private String  uid;

    public LoginToken() {
    }

    public LoginToken(String id, String login_name, String uid) {
        this.id = id;
        this.login_name = login_name;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
