package com.hotelbooking.hotelbooking.dtos;

import java.io.Serializable;

public class HotelDTO implements Serializable {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String description;
    private Long hotelStatusID;

    public HotelDTO() {
    }

    public HotelDTO(Long id, String email, String name, String phone, String description, Long hotelStatusID) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.hotelStatusID = hotelStatusID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHotelStatusID() {
        return hotelStatusID;
    }

    public void setHotelStatusID(Long hotelStatusID) {
        this.hotelStatusID = hotelStatusID;
    }
}
