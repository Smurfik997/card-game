package ua.kpi.cardgame.entities;

import java.util.Objects;

public class User {
    private int userId;
    private String login;
    private String password;
    private int rate;
    private String status;


    public User() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(int userId, String login, String password, int rate, String status) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.rate = rate;
        this.status = status;
    }

    public User(int userId, String login, String password, int rate) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.rate = rate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rate=" + rate +
                '}';
    }
}
