package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class AccountInformationResponse implements Serializable {
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String idCard;
    private Long roleID;

    public AccountInformationResponse() {
    }

    public AccountInformationResponse(String email, String fullName, String phone,
                                      String address, String idCard, Long roleID) {
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.idCard = idCard;
        this.roleID = roleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }
}
