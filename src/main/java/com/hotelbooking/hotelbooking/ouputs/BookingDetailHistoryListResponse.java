package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;
import java.util.List;

public class BookingDetailHistoryListResponse extends MyPaging implements Serializable {
    private List<BookingDetailHistoryResponse> bookingDetailHistoryList;

    public BookingDetailHistoryListResponse() {
    }

    public BookingDetailHistoryListResponse(Integer totalPage, Integer previousPage, Integer currentPage,
                                            Integer nextPage, Integer pageSize,
                                            List<BookingDetailHistoryResponse> bookingDetailHistoryList) {
        super(totalPage, previousPage, currentPage, nextPage, pageSize);
        this.bookingDetailHistoryList = bookingDetailHistoryList;
    }

    public List<BookingDetailHistoryResponse> getBookingDetailHistoryList() {
        return bookingDetailHistoryList;
    }

    public void setBookingDetailHistoryList(List<BookingDetailHistoryResponse> bookingDetailHistoryList) {
        this.bookingDetailHistoryList = bookingDetailHistoryList;
    }
}
