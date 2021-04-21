package com.epamlearning.moksiakova.lessons.lesson9.pojo;

import java.util.Objects;

public class User {
    private Human human;
    private String userNick;
    private int userId;

    public User(Human human, String userNick, int userId) {
        this.human = human;
        this.userNick = userNick;
        this.userId = userId;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(human, user.human) && userNick.equals(user.userNick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(human, userNick, userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "human=" + human +
                ", userNick='" + userNick + '\'' +
                ", userId=" + userId +
                '}';
    }
}
