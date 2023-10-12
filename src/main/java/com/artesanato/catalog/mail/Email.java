package com.artesanato.catalog.mail;

public class Email {

    private String name;
    private String email;
    private String msg;

    public Email(String name, String email, String msg) {
        this.name = name;
        this.email = email;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMsg() {
        return msg;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
