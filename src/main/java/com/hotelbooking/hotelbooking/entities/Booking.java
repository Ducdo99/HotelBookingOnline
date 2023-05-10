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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Booking")
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "booking_date", nullable = false)
    private Timestamp bookingDate;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_status_id")
    private BookingStatus bookingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_request_status_id")
    private BookingRequestStatus bookingRequestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_of_account_id")
    private CouponOfAccount couponOfAccount;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingDetail> bookingDetails = new ArrayList<>();

    public Booking() {
    }

    public Booking(Timestamp bookingDate, Double total) {
        this.bookingDate = bookingDate;
        this.total = total;
    }

    public Booking(Timestamp bookingDate, Double total, BookingStatus bookingStatus,
                   BookingRequestStatus bookingRequestStatus, Account account,
                   CouponOfAccount couponOfAccount, List<BookingDetail> bookingDetails) {
        this.bookingDate = bookingDate;
        this.total = total;
        this.bookingStatus = bookingStatus;
        this.bookingRequestStatus = bookingRequestStatus;
        this.account = account;
        this.couponOfAccount = couponOfAccount;
        this.bookingDetails = bookingDetails;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Double getTotal() {
        Double temp = this.total;
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingRequestStatus getBookingRequestStatus() {
        return bookingRequestStatus;
    }

    public void setBookingRequestStatus(BookingRequestStatus bookingRequestStatus) {
        this.bookingRequestStatus = bookingRequestStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CouponOfAccount getCouponOfAccount() {
        return couponOfAccount;
    }

    public void setCouponOfAccount(CouponOfAccount couponOfAccount) {
        this.couponOfAccount = couponOfAccount;
    }

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
