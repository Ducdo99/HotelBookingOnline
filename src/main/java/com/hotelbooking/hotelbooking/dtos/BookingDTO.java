package com.hotelbooking.hotelbooking.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookingDTO implements Serializable {
    private Long id;
    private Timestamp bookingDate;
    private Double total;
    private Long bookingStatusID;
    private Long accountID;
    private Long couponOfAccountID;
    private Long bookingRequestStatusID;

    public BookingDTO() {
    }

    public BookingDTO(Long id, Timestamp bookingDate, Double total, Long bookingStatusID,
                      Long accountID, Long couponOfAccountID, Long bookingRequestStatusID) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.total = total;
        this.bookingStatusID = bookingStatusID;
        this.accountID = accountID;
        this.couponOfAccountID = couponOfAccountID;
        this.bookingRequestStatusID = bookingRequestStatusID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getBookingStatusID() {
        return bookingStatusID;
    }

    public void setBookingStatusID(Long bookingStatusID) {
        this.bookingStatusID = bookingStatusID;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public Long getCouponOfAccountID() {
        return couponOfAccountID;
    }

    public void setCouponOfAccountID(Long couponOfAccountID) {
        this.couponOfAccountID = couponOfAccountID;
    }

    public Long getBookingRequestStatusID() {
        return bookingRequestStatusID;
    }

    public void setBookingRequestStatusID(Long bookingRequestStatusID) {
        this.bookingRequestStatusID = bookingRequestStatusID;
    }
}
