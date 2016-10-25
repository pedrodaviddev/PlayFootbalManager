package com.pedrodavidlp.footballmanager.domain.model;

public class Group {
    private String id;
    private String password;
    private int numberPlayers;
    private int date;

    public Group() {

    }

    public Group(String id, String password, int numberPlayers, int date) {
        this.id = id;
        this.password = password;
        this.numberPlayers = numberPlayers;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
