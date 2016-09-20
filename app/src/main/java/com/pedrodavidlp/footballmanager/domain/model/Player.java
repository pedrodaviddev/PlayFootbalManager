package com.pedrodavidlp.footballmanager.domain.model;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public class Player {
    private String name;
    private int skill;
    private boolean isOrganizator;
    private boolean isAdmin;

    public Player() {
    }

    public Player(String name, int skill, boolean isOrganizator, boolean isAdmin) {
        this.name = name;
        this.skill = skill;
        this.isOrganizator = isOrganizator;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public boolean isOrganizator() {
        return isOrganizator;
    }

    public void setOrganizator(boolean organizator) {
        isOrganizator = organizator;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
