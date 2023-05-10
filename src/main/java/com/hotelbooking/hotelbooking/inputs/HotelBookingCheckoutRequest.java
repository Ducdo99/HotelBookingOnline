package com.hotelbooking.hotelbooking.inputs;

import java.io.Serializable;
import java.util.List;

public class HotelBookingCheckoutRequest implements Serializable {
    private Double total;
    private Long couponOfAccountID;
    private List<HotelBookingDetailRequest> hotelBookingDetailList;

    public HotelBookingCheckoutRequest() {
    }

    public HotelBookingCheckoutRequest(Double total, Long couponOfAccountID,
                                       List<HotelBookingDetailRequest> hotelBookingDetailList) {
        this.total = total;
        this.couponOfAccountID = couponOfAccountID;
        this.hotelBookingDetailList = hotelBookingDetailList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getCouponOfAccountID() {
        return couponOfAccountID;
    }

    public void setCouponOfAccountID(Long couponOfAccountID) {
        this.couponOfAccountID = couponOfAccountID;
    }

    public List<HotelBookingDetailRequest> getHotelBookingDetailList() {
        return hotelBookingDetailList;
    }

    public void setHotelBookingDetailList(List<HotelBookingDetailRequest> hotelBookingDetailList) {
        this.hotelBookingDetailList = hotelBookingDetailList;
    }
}

