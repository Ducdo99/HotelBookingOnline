package com.hotelbooking.hotelbooking.ouputs;

import com.hotelbooking.hotelbooking.dtos.RoomDTO;

import java.io.Serializable;

public class AvailableRoomInformationResponse implements Serializable {
    private String name;
    private String description;
    private String price;
    private Integer day;
    private Integer maxPerson;
    private String roomTypeName;
    private String hotelName;
    private String hotelPhoneNumber;
    private String hotelEmail;
    private String hotelNumber;
    private String hotelStreet;
    private String hotelWardName;
    private String hotelDistrictName;
    private String hotelCityName;

    public AvailableRoomInformationResponse() {
    }

    public AvailableRoomInformationResponse(String name, String description, String price, Integer day,
                                            Integer maxPerson, String roomTypeName, String hotelName,
                                            String hotelPhoneNumber, String hotelEmail, String hotelNumber,
                                            String hotelStreet, String hotelWardName, String hotelDistrictName,
                                            String hotelCityName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.day = day;
        this.maxPerson = maxPerson;
        this.roomTypeName = roomTypeName;
        this.hotelName = hotelName;
        this.hotelPhoneNumber = hotelPhoneNumber;
        this.hotelEmail = hotelEmail;
        this.hotelNumber = hotelNumber;
        this.hotelStreet = hotelStreet;
        this.hotelWardName = hotelWardName;
        this.hotelDistrictName = hotelDistrictName;
        this.hotelCityName = hotelCityName;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelNumber() {
        return hotelNumber;
    }

    public void setHotelNumber(String hotelNumber) {
        this.hotelNumber = hotelNumber;
    }

    public String getHotelStreet() {
        return hotelStreet;
    }

    public void setHotelStreet(String hotelStreet) {
        this.hotelStreet = hotelStreet;
    }

    public String getHotelWardName() {
        return hotelWardName;
    }

    public void setHotelWardName(String hotelWardName) {
        this.hotelWardName = hotelWardName;
    }

    public String getHotelDistrictName() {
        return hotelDistrictName;
    }

    public void setHotelDistrictName(String hotelDistrictName) {
        this.hotelDistrictName = hotelDistrictName;
    }

    public String getHotelCityName() {
        return hotelCityName;
    }

    public void setHotelCityName(String hotelCityName) {
        this.hotelCityName = hotelCityName;
    }
}
