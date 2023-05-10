package com.hotelbooking.hotelbooking.dtos;

import java.io.Serializable;
import java.sql.Timestamp;


public class AccountDTO implements Serializable {
    private Long id;
    private String email;
    private String pwd;
    private String fullName;
    private String phone;
    private Timestamp createdDate;
    private String address;
    private String idCard;
    private Long roleID;
    private Long accountStatusID;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String email, String pwd, String fullName, String phone, Timestamp createdDate,
                      String address, String idCard, Long roleID, Long accountStatusID) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.fullName = fullName;
        this.phone = phone;
        this.createdDate = createdDate;
        this.address = address;
        this.idCard = idCard;
        this.roleID = roleID;
        this.accountStatusID = accountStatusID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public Long getAccountStatusID() {
        return accountStatusID;
    }

    public void setAccountStatusID(Long accountStatusID) {
        this.accountStatusID = accountStatusID;
    }
}
