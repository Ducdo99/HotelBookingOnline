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

@Entity(name = "Account")
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 250, nullable = false, unique = true)
    private String email;

    @Column(name = "pwd", length = 250, nullable = false)
    private String pwd;

    @Column(name = "fullname", length = 250, nullable = false)
    private String fullName;

    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "address", length = 250, nullable = true)
    private String address;

    @Column(name = "id_card", length = 12, nullable = true)
    private String idCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_status_id")
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponOfAccount> couponOfAccounts = new ArrayList<>();

    public Account() {

    }

    public Account(String email, String pwd, String fullName, String phone,
                   Timestamp createdDate, String address, String idCard) {
        this.email = email;
        this.pwd = pwd;
        this.fullName = fullName;
        this.phone = phone;
        this.createdDate = createdDate;
        this.address = address;
        this.idCard = idCard;
    }

    public Account(String email, String pwd, String fullName, String phone,
                   Timestamp createdDate, String address, String idCard, Role role,
                   AccountStatus accountStatus, List<Booking> bookings, List<CouponOfAccount> couponOfAccounts) {
        this.email = email;
        this.pwd = pwd;
        this.fullName = fullName;
        this.phone = phone;
        this.createdDate = createdDate;
        this.address = address;
        this.idCard = idCard;
        this.role = role;
        this.accountStatus = accountStatus;
        this.bookings = bookings;
        this.couponOfAccounts = couponOfAccounts;
    }

    public Long getId() {
        return id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<CouponOfAccount> getCouponOfAccounts() {
        return couponOfAccounts;
    }

    public void setCouponOfAccounts(List<CouponOfAccount> couponOfAccounts) {
        this.couponOfAccounts = couponOfAccounts;
    }
}
