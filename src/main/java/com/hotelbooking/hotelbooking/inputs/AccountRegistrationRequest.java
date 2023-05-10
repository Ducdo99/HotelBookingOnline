package com.hotelbooking.hotelbooking.inputs;

import java.io.Serializable;

public class AccountRegistrationRequest implements Serializable {
    private String email;
    private String pwd;
    private String fullName;
    private String phone;
    private String address;
    private String idCard;

    public AccountRegistrationRequest() {
    }

    public AccountRegistrationRequest(String email, String pwd, String fullName,
                                      String phone, String address, String idCard) {
        this.email = email;
        this.pwd = pwd;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
