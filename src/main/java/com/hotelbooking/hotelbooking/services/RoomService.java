package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.converters.RoomConverter;
import com.hotelbooking.hotelbooking.entities.HotelAddress;
import com.hotelbooking.hotelbooking.entities.Room;
import com.hotelbooking.hotelbooking.entities.RoomType;
import com.hotelbooking.hotelbooking.ouputs.AvailableRoomInformationResponse;
import com.hotelbooking.hotelbooking.ouputs.AvailableRoomListResponse;
import com.hotelbooking.hotelbooking.repositories.HotelAddressRepository;
import com.hotelbooking.hotelbooking.repositories.RoomRepository;
import com.hotelbooking.hotelbooking.repositories.RoomStatusRepository;
import com.hotelbooking.hotelbooking.repositories.RoomTypeRepository;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomStatusRepository roomStatusRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private HotelAddressRepository hotelAddressRepository;

    @Autowired
    private RoomConverter roomConverter;

    @Autowired
    private Utility utility;

    public AvailableRoomListResponse getAvailableRoomsInfo(String hotelName, String district, String city,
                                                           String checkInDate, String checkOutDate,
                                                           int page, int pageSize) {
        Integer totalAvailableRoom = hotelName.trim().equalsIgnoreCase("")
                ? roomRepository.countAllAvailableRoom(null, district.trim(), city.trim(),
                utility.formatDateTime(checkInDate.trim()), utility.formatDateTime(checkOutDate.trim()),
                MyConstantVariables.AVAILABLE_ROOM_STATUS_NAME.trim(),
                MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim())
                : roomRepository.countAllAvailableRoom(hotelName.trim(), district.trim(), city.trim(),
                utility.formatDateTime(checkInDate.trim()), utility.formatDateTime(checkOutDate.trim()),
                MyConstantVariables.AVAILABLE_ROOM_STATUS_NAME.trim(),
                MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim());

        if (totalAvailableRoom != null) {
            if (totalAvailableRoom > 0) {
                Integer totalPage = Double.valueOf(Math.ceil(
                        totalAvailableRoom.doubleValue() / Integer.valueOf(pageSize).doubleValue())).intValue();
                if (page > totalPage) {
                    throw new IllegalArgumentException("The data hasn't enough for 1 page");
                }
                List<AvailableRoomInformationResponse> resultList = null;
                int pageIndex = page - 1;
                Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.unsorted());
                List<Room> rooms = hotelName.trim().equalsIgnoreCase("")
                        ? roomRepository.findAllAvailableRoom(null, district.trim(), city.trim(),
                        utility.formatDateTime(checkInDate.trim()), utility.formatDateTime(checkOutDate.trim()),
                        MyConstantVariables.AVAILABLE_ROOM_STATUS_NAME.trim(),
                        MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), pageable)
                        : roomRepository.findAllAvailableRoom(hotelName.trim(), district.trim(), city.trim(),
                        utility.formatDateTime(checkInDate.trim()), utility.formatDateTime(checkOutDate.trim()),
                        MyConstantVariables.AVAILABLE_ROOM_STATUS_NAME.trim(),
                        MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), pageable);

                if (rooms != null) {
                    resultList = new ArrayList<>();
                    for (Room room : rooms) {
                        HotelAddress hotelAddressEntity = hotelAddressRepository.getOne(room.getHotel().getId());
                        RoomType roomType = roomTypeRepository.getOne(room.getRoomType().getId());
                        AvailableRoomInformationResponse result = new AvailableRoomInformationResponse(
                                room.getName().trim(), room.getDescription().trim(),
                                utility.convertToDecimalFormat(
                                        MyConstantVariables.THOUSANDS_AND_THOUSANDTHS_UNIT.trim(),
                                        room.getPrice()).trim(),
                                room.getDay(), room.getMaxPerson(), roomType.getName().trim(),
                                room.getHotel().getName().trim(), room.getHotel().getPhone().trim(),
                                room.getHotel().getEmail().trim(), hotelAddressEntity.getNumber().trim(),
                                hotelAddressEntity.getStreet().trim(), hotelAddressEntity.getWardName().trim(),
                                hotelAddressEntity.getDistrictName().trim(), hotelAddressEntity.getCityName().trim());
                        resultList.add(result);
                    }
                }
                if (resultList != null) {
                    if (!resultList.isEmpty()) {
                        AvailableRoomListResponse availableRoomListResponse = new AvailableRoomListResponse();
                        availableRoomListResponse.setTotalPage(totalPage);
                        availableRoomListResponse.setCurrentPage(page);
                        availableRoomListResponse.setPreviousPage(page - 1 < 1 ? 1 : page - 1);
                        availableRoomListResponse.setNextPage(page + 1 > totalPage ? totalPage : page + 1);
                        availableRoomListResponse.setPageSize(pageSize);
                        availableRoomListResponse.setAvailableRoomListResponse(resultList);
                        return availableRoomListResponse;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Not found any room");
    }

    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Room changeStatusOfRoom(Room entity, String roomStatusName) throws Exception {
        entity.setRoomStatus(roomStatusRepository.findByName(roomStatusName.trim()));
        return roomRepository.save(entity);
    }

}
