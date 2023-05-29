package com.example.app;

public class User {//класс для представления пользователей(конференций)
    private String name;
    private String own;
    private String kol;
    private String date;

    public User(String name, String own, String kol, String date) {
        this.name = name;
        this.own = own;
        this.kol = kol;
        this.date = date;
    }

    public User() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getKol() {
        return kol;
    }

    public void setKol(String kol) {
        this.kol = kol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
