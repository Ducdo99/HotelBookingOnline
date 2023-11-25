package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.converters.HotelConverter;
import com.hotelbooking.hotelbooking.entities.Hotel;
import com.hotelbooking.hotelbooking.entities.HotelAddress;
import com.hotelbooking.hotelbooking.ouputs.HotelInformationResponse;
import com.hotelbooking.hotelbooking.ouputs.HotelListResponse;
import com.hotelbooking.hotelbooking.repositories.HotelAddressRepository;
import com.hotelbooking.hotelbooking.repositories.HotelRepository;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelAddressRepository hotelAddressRepository;

    @Autowired
    private HotelConverter hotelConverter;

    public HotelListResponse getAllHotelAvailable(int pageSize, int page)
            throws IllegalArgumentException {
        Integer totalHotel = hotelRepository.countHotelByRoomStatusName(
                MyConstantVariables.AVAILABLE_ROOM_STATUS_NAME.trim());

        if (totalHotel != null) {
            if (totalHotel > 0) {
                Integer totalPage = Double.valueOf(Math.ceil(
                        totalHotel.doubleValue() / Integer.valueOf(pageSize).doubleValue())).intValue();
                if (page > totalPage) {
                    throw new IllegalArgumentException("The data hasn't enough for 1 page");
                }
                List<HotelInformationResponse> hotelInformationResponses = null;
                int pageIndex = page - 1;
                Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.unsorted());
                List<Hotel> hotels = hotelRepository.getAllByRoomStatusName(
                        MyConstantVariables.AVAILABLE_ROOM_STATUS_NAME.trim(), pageable);

                if (hotels != null) {
                    hotelInformationResponses = new ArrayList<>();
                    for (Hotel hotel : hotels) {
                        HotelAddress hotelAddressEntity = hotelAddressRepository.getOne(hotel.getId());
                        HotelInformationResponse hotelInformationResponse = new HotelInformationResponse(
                                hotel.getEmail().trim(), hotel.getName().trim(), hotel.getPhone(),
                                hotel.getDescription().trim(), hotelAddressEntity.getNumber().trim(),
                                hotelAddressEntity.getStreet().trim(), hotelAddressEntity.getWardName().trim(),
                                hotelAddressEntity.getDistrictName().trim(), hotelAddressEntity.getCityName().trim());
                        hotelInformationResponses.add(hotelInformationResponse);
                    }
                }
                if (hotelInformationResponses != null) {
                    if (!hotelInformationResponses.isEmpty()) {
                        HotelListResponse hotelListResponse = new HotelListResponse();
                        hotelListResponse.setTotalPage(totalPage);
                        hotelListResponse.setCurrentPage(page);
                        hotelListResponse.setPreviousPage(page - 1 < 1 ? 1 : page - 1);
                        hotelListResponse.setNextPage(page + 1 > totalPage ? totalPage : page + 1);
                        hotelListResponse.setPageSize(pageSize);
                        hotelListResponse.setHotels(hotelInformationResponses);
                        return hotelListResponse;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Not found any hotel");
    }
}