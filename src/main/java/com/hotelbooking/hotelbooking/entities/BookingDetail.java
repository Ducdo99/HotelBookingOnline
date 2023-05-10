package com.hotelbooking.hotelbooking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "BookingDetail")
@Table(name = "booking_detail")
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hotel_name", length = 250, nullable = false)
    private String hotelName;

    @Column(name = "room_name", length = 250, nullable = false)
    private String roomName;

    @Column(name = "room_type", length = 250, nullable = false)
    private String roomType;

    @Column(name = "days", nullable = false)
    private Integer days;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "sub_total", nullable = false)
    private Double subTotal;

    @Column(name = "check_in_date", nullable = false)
    private Timestamp checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private Timestamp checkOutDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public BookingDetail() {
    }

    public BookingDetail(String hotelName, String roomName, String roomType, Integer days, Double price,
                         Double subTotal, Timestamp checkInDate, Timestamp checkOutDate) {
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.roomType = roomType;
        this.days = days;
        this.price = price;
        this.subTotal = subTotal;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public BookingDetail(String hotelName, String roomName, String roomType, Integer days, Double price,
                         Double subTotal, Timestamp checkInDate, Timestamp checkOutDate, Room room, Booking booking) {
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.roomType = roomType;
        this.days = days;
        this.price = price;
        this.subTotal = subTotal;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.booking = booking;
    }

    public Long getId() {
        return id;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
