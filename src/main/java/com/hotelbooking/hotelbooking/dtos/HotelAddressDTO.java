package com.hotelbooking.hotelbooking.dtos;

import java.io.Serializable;

public class HotelAddressDTO implements Serializable {
    private Long id;
    private String number;
    private String street;
    private String wardName;
    private String districtName;
    private String cityName;

    public HotelAddressDTO() {
    }

    public HotelAddressDTO(Long id, String number, String street, String wardName,
                           String districtName, String cityName) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.wardName = wardName;
        this.districtName = districtName;
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
