package com.epamlearning.moksiakova.lessons.lesson9.pojo;

import java.util.Objects;

public class User {
    private String userNick;
    private int userId;

    public User() {}

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
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(userNick, user.userNick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNick, userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userNick='" + userNick + '\'' +
                ", userId=" + userId +
                '}';
    }
}
