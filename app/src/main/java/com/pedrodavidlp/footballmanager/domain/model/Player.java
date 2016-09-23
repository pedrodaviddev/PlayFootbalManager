package com.pedrodavidlp.footballmanager.domain.model;

import java.util.List;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public class Player {
    private String nickname;
    private String name;
    private int skill;
    private boolean isOrganizator;
    private boolean isAdmin;

    public Player() {
    }

    public Player(String nickname, String name, int skill, boolean isOrganizator, boolean isAdmin) {
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
