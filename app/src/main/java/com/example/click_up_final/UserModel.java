package com.example.click_up_final;

public class UserModel {
    public String userName;
    public String userNickname;
    public String userUID;

    public UserModel() {
    }

    public UserModel(String userName, String userNickname, String userUID) {
        this.userName = userName;
        this.userNickname = userNickname;
        this.userUID = userUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }
}