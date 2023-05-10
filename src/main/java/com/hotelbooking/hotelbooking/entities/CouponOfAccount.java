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

@Entity(name = "CouponOfAccount")
@Table(name = "coupon_of_account")
public class CouponOfAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "coupon_code", length = 250, nullable = false)
    private String couponCode;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "remaining_quantity", nullable = false)
    private Integer remainingQuantity;

    @Column(name = "percentage", nullable = false)
    private Integer percentage;

    @Column(name = "expiry_date", nullable = false)
    private Timestamp expiryDate;

    @OneToMany(mappedBy = "couponOfAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_of_account_status_id")
    private CouponOfAccountStatus couponOfAccountStatus;

    public CouponOfAccount() {
    }

    public CouponOfAccount(String couponCode, String description, Integer remainingQuantity,
                           Integer percentage, Timestamp expiryDate) {
        this.couponCode = couponCode;
        this.description = description;
        this.remainingQuantity = remainingQuantity;
        this.percentage = percentage;
        this.expiryDate = expiryDate;
    }

    public CouponOfAccount(String couponCode, String description, Integer remainingQuantity, Integer percentage,
                           Timestamp expiryDate, List<Booking> bookings, Coupon coupon, Account account,
                           CouponOfAccountStatus couponOfAccountStatus) {
        this.couponCode = couponCode;
        this.description = description;
        this.remainingQuantity = remainingQuantity;
        this.percentage = percentage;
        this.expiryDate = expiryDate;
        this.bookings = bookings;
        this.coupon = coupon;
        this.account = account;
        this.couponOfAccountStatus = couponOfAccountStatus;
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CouponOfAccountStatus getCouponOfAccountStatus() {
        return couponOfAccountStatus;
    }

    public void setCouponOfAccountStatus(CouponOfAccountStatus couponOfAccountStatus) {
        this.couponOfAccountStatus = couponOfAccountStatus;
    }
}
