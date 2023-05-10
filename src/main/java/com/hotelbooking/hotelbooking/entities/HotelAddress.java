package com.hotelbooking.hotelbooking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "HotelAddress")
@Table(name = "hotel_address")
public class HotelAddress {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "number", nullable = false, length = 250)
    private String number;
    @Column(name = "street", nullable = false, length = 250)
    private String street;
    @Column(name = "ward_name", nullable = false, length = 250)
    private String wardName;
    @Column(name = "district_name", nullable = false, length = 250)
    private String districtName;
    @Column(name = "city_name", nullable = false, length = 250)
    private String cityName;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Hotel hotel;

    public HotelAddress() {
    }

    public HotelAddress(String number, String street, String wardName, String districtName, String cityName) {
        this.number = number;
        this.street = street;
        this.wardName = wardName;
        this.districtName = districtName;
        this.cityName = cityName;
    }

    public HotelAddress(String number, String street, String wardName,
                        String districtName, String cityName, Hotel hotel) {
        this.number = number;
        this.street = street;
        this.wardName = wardName;
        this.districtName = districtName;
        this.cityName = cityName;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
