package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class BookingDetailHistoryResponse implements Serializable {
    private String hotelName;
    private String roomName;
    private String roomType;
    private Integer days;
    private String price;
    private String subTotal;
    private String checkInDate;
    private String checkOutDate;

    public BookingDetailHistoryResponse() {
    }

    public BookingDetailHistoryResponse(String hotelName, String roomName, String roomType, Integer days, String price,
                                        String subTotal, String checkInDate, String checkOutDate) {
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.roomType = roomType;
        this.days = days;
        this.price = price;
        this.subTotal = subTotal;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
