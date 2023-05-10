package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class CouponOfAccountInformationResponse implements Serializable {
    private Long id;
    private String couponCode;
    private String description;
    private Integer remainingQuantity;
    private Integer percentage;
    private String expiryDate;

    public CouponOfAccountInformationResponse() {
    }

    public CouponOfAccountInformationResponse(Long id, String couponCode, String description,
                                              Integer remainingQuantity, Integer percentage, String expiryDate) {
        this.id = id;
        this.couponCode = couponCode;
        this.description = description;
        this.remainingQuantity = remainingQuantity;
        this.percentage = percentage;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
