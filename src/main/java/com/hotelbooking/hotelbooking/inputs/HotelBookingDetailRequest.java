package com.hotelbooking.hotelbooking.inputs;

import java.io.Serializable;

public class HotelBookingDetailRequest implements Serializable {
    private String hotelName;
    private String roomName;
    private String roomTypeName;
    private Integer rentalDays;
    private Double roomPrice;
    private Double subTotal;
    private String checkInDate;
    private String checkOutDate;
    private Long roomID;

    public HotelBookingDetailRequest() {
    }

    public HotelBookingDetailRequest(String hotelName, String roomName, String roomTypeName,
                                     Integer rentalDays, Double roomPrice, Double subTotal,
                                     String checkInDate, String checkOutDate, Long roomID) {
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.roomTypeName = roomTypeName;
        this.rentalDays = rentalDays;
        this.roomPrice = roomPrice;
        this.subTotal = subTotal;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomID = roomID;
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

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
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

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }
}
