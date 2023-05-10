package com.hotelbooking.hotelbooking.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookingDetailDTO implements Serializable {
    private Long id;
    private String hotelName;
    private String roomName;
    private String roomType;
    private Integer days;
    private Double price;
    private Double subTotal;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private Long bookingID;
    private Long roomID;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(Long id, String hotelName, String roomName, String roomType, Integer days,
                            Double price, Double subTotal, Timestamp checkInDate, Timestamp checkOutDate,
                            Long bookingID, Long roomID) {
        this.id = id;
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.roomType = roomType;
        this.days = days;
        this.price = price;
        this.subTotal = subTotal;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingID = bookingID;
        this.roomID = roomID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }
}
