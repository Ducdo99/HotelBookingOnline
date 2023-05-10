package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;
import java.util.List;

public class HotelListResponse extends MyPaging implements Serializable {
    private List<HotelInformationResponse> hotels;

    public HotelListResponse() {
    }

    public HotelListResponse(Integer totalPage, Integer previousPage, Integer currentPage, Integer nextPage,
                             Integer pageSize, List<HotelInformationResponse> hotels) {
        super(totalPage, previousPage, currentPage, nextPage, pageSize);
        this.hotels = hotels;
    }

    public List<HotelInformationResponse> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelInformationResponse> hotels) {
        this.hotels = hotels;
    }

}
