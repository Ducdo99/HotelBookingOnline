package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class HotelInformationResponse implements Serializable {
    private String email;
    private String name;
    private String phone;
    private String description;
    private String number;
    private String street;
    private String wardName;
    private String districtName;
    private String cityName;

    public HotelInformationResponse() {
    }

    public HotelInformationResponse(String email, String name, String phone, String description, String number,
                                    String street, String wardName, String districtName, String cityName) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.number = number;
        this.street = street;
        this.wardName = wardName;
        this.districtName = districtName;
        this.cityName = cityName;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
