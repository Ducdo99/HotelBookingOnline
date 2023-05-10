package com.hotelbooking.hotelbooking.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CouponOfAccountStatus")
@Table(name = "coupon_of_account_status")
public class CouponOfAccountStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @OneToMany(mappedBy = "couponOfAccountStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponOfAccount> couponOfAccounts = new ArrayList<>();

    public CouponOfAccountStatus() {
    }

    public CouponOfAccountStatus(String name) {
        this.name = name;
    }

    public CouponOfAccountStatus(String name, List<CouponOfAccount> couponOfAccounts) {
        this.name = name;
        this.couponOfAccounts = couponOfAccounts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CouponOfAccount> getCouponOfAccounts() {
        return couponOfAccounts;
    }

    public void setCouponOfAccounts(List<CouponOfAccount> couponOfAccounts) {
        this.couponOfAccounts = couponOfAccounts;
    }
}
