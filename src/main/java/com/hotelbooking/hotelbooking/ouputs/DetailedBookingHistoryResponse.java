package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class DetailedBookingHistoryResponse implements Serializable {

    private Long bookingID;
    private String bookingDate;
    private String total;
    private String couponCodeOfTheAccount;
    private String bookingRequestStatusName;
    private BookingDetailHistoryListResponse bookingDetailHistory;

    public DetailedBookingHistoryResponse() {
    }

    public DetailedBookingHistoryResponse(Long bookingID, String bookingDate, String total,
                                          String couponCodeOfTheAccount, String bookingRequestStatusName,
                                          BookingDetailHistoryListResponse bookingDetailHistory) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.total = total;
        this.couponCodeOfTheAccount = couponCodeOfTheAccount;
        this.bookingRequestStatusName = bookingRequestStatusName;
        this.bookingDetailHistory = bookingDetailHistory;
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

    public BookingDetailHistoryListResponse getBookingDetailHistory() {
        return bookingDetailHistory;
    }

    public void setBookingDetailHistory(BookingDetailHistoryListResponse bookingDetailHistory) {
        this.bookingDetailHistory = bookingDetailHistory;
    }
}
