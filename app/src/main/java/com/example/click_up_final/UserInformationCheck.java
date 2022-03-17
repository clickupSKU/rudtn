package com.example.click_up_final;

public class UserInformationCheck {
    private String name;
    private String birth;
    private String phone;
    private String nickname;

    public UserInformationCheck() {

    }

    public UserInformationCheck(String name, String birth, String phone, String nickname) {
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}