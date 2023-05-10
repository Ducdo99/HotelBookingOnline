package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;
import java.util.List;

public class BookingHistoryListResponse extends MyPaging implements Serializable {

    private List<BookingHistoryResponse> bookingHistoryList;

    public BookingHistoryListResponse() {
    }

    public BookingHistoryListResponse(Integer totalPage, Integer previousPage, Integer currentPage, Integer nextPage,
                                      Integer pageSize, List<BookingHistoryResponse> bookingHistoryList) {
        super(totalPage, previousPage, currentPage, nextPage, pageSize);
        this.bookingHistoryList = bookingHistoryList;
    }

    public List<BookingHistoryResponse> getBookingHistoryList() {
        return bookingHistoryList;
    }

    public void setBookingHistoryList(List<BookingHistoryResponse> bookingHistoryList) {
        this.bookingHistoryList = bookingHistoryList;
    }
}
