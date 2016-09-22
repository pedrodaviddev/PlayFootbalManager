package com.pedrodavidlp.footballmanager.domain.model;

import java.util.List;

public class Group {
    private List<Player> players;
    private String id;

    public Group() {

    }

    public Group(List<Player> players, String id) {
        this.players = players;
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
