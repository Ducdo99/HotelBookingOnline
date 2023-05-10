package com.hotelbooking.hotelbooking.dtos;

import java.io.Serializable;

public class RoomDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer day;
    private Integer maxPerson;
    private Long roomTypeID;
    private Long hotelID;
    private Long roomStatusID;

    public RoomDTO() {
    }

    public RoomDTO(Long id, String name, String description, Double price, Integer day,
                   Integer maxPerson, Long roomTypeID, Long hotelID, Long roomStatusID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.day = day;
        this.maxPerson = maxPerson;
        this.roomTypeID = roomTypeID;
        this.hotelID = hotelID;
        this.roomStatusID = roomStatusID;
    }

    public RoomDTO(String name, String description, Double price, Integer day, Integer maxPerson) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.day = day;
        this.maxPerson = maxPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(Integer maxPerson) {
        this.maxPerson = maxPerson;
    }

    public Long getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(Long roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public Long getHotelID() {
        return hotelID;
    }

    public void setHotelID(Long hotelID) {
        this.hotelID = hotelID;
    }

    public Long getRoomStatusID() {
        return roomStatusID;
    }

    public void setRoomStatusID(Long roomStatusID) {
        this.roomStatusID = roomStatusID;
    }
}
