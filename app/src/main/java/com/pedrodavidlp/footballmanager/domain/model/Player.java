package com.pedrodavidlp.footballmanager.domain.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by PedroDavidLP on 14/9/16.
 */
public class Player {
    private String nickname;
    private int skill;
    private boolean isOrganizator;
    private boolean isAdmin;

    public Player() {
    }

    public Player(@NonNull String nickname, int skill, boolean isOrganizator, boolean isAdmin) {
        this.nickname = nickname;
        this.skill = skill;
        this.isOrganizator = isOrganizator;
        this.isAdmin = isAdmin;
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
