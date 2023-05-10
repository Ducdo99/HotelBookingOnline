package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class SuccessfulAuthenticationAccountResponse implements Serializable {
    private String fullName;
    private Long roleID;
    private String tokenString;

    public SuccessfulAuthenticationAccountResponse() {
    }

    public SuccessfulAuthenticationAccountResponse(String fullName, Long roleID, String tokenString) {
        this.fullName = fullName;
        this.roleID = roleID;
        this.tokenString = tokenString;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }
}
