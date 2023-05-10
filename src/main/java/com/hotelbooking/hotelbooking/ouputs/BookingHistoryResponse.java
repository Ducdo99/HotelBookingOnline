package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class BookingHistoryResponse implements Serializable {
    private Long bookingID;
    private String bookingDate;
    private String total;
    private String couponCodeOfTheAccount;
    private String bookingRequestStatusName;

    public BookingHistoryResponse() {
    }

    public BookingHistoryResponse(Long bookingID, String bookingDate, String total,
                                  String couponCodeOfTheAccount, String bookingRequestStatusName) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.total = total;
        this.couponCodeOfTheAccount = couponCodeOfTheAccount;
        this.bookingRequestStatusName = bookingRequestStatusName;
    }

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCouponCodeOfTheAccount() {
        return couponCodeOfTheAccount;
    }

    public void setCouponCodeOfTheAccount(String couponCodeOfTheAccount) {
        this.couponCodeOfTheAccount = couponCodeOfTheAccount;
    }

    public String getBookingRequestStatusName() {
        return bookingRequestStatusName;
    }

    public void setBookingRequestStatusName(String bookingRequestStatusName) {
        this.bookingRequestStatusName = bookingRequestStatusName;
    }
}
