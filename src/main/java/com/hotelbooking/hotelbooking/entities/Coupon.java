package com.hotelbooking.hotelbooking.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Coupon")
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "coupon_code", length = 250, nullable = false, unique = true)
    private String couponCode;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "remaining_quantity", nullable = false)
    private Integer remainingQuantity;

    @Column(name = "percentage", nullable = false)
    private Integer percentage;

    @Column(name = "expiry_date", nullable = false)
    private Timestamp expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_status_id")
    private CouponStatus couponStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_type_id")
    private CouponType couponType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponOfAccount> couponOfAccounts = new ArrayList<>();

    public Coupon() {
    }

    public Coupon(String couponCode, String description, Integer remainingQuantity,
                  Integer percentage, Timestamp expiryDate) {
        this.couponCode = couponCode;
        this.description = description;
        this.remainingQuantity = remainingQuantity;
        this.percentage = percentage;
        this.expiryDate = expiryDate;
    }

    public Coupon(String couponCode, String description, Integer remainingQuantity, Integer percentage,
                  Timestamp expiryDate, CouponStatus couponStatus, CouponType couponType, Hotel hotel,
                  List<CouponOfAccount> couponOfAccounts) {
        this.couponCode = couponCode;
        this.description = description;
        this.remainingQuantity = remainingQuantity;
        this.percentage = percentage;
        this.expiryDate = expiryDate;
        this.couponStatus = couponStatus;
        this.couponType = couponType;
        this.hotel = hotel;
        this.couponOfAccounts = couponOfAccounts;
    }

    public Long getId() {
        return id;
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

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public CouponStatus getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(CouponStatus couponStatus) {
        this.couponStatus = couponStatus;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<CouponOfAccount> getCouponOfAccounts() {
        return couponOfAccounts;
    }

    public void setCouponOfAccounts(List<CouponOfAccount> couponOfAccounts) {
        this.couponOfAccounts = couponOfAccounts;
    }
}
