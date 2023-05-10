package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.converters.BookingDetailConverter;
import com.hotelbooking.hotelbooking.entities.Booking;
import com.hotelbooking.hotelbooking.entities.BookingDetail;
import com.hotelbooking.hotelbooking.entities.Room;
import com.hotelbooking.hotelbooking.inputs.HotelBookingDetailRequest;
import com.hotelbooking.hotelbooking.repositories.BookingDetailRepository;
import com.hotelbooking.hotelbooking.repositories.BookingRepository;
import com.hotelbooking.hotelbooking.repositories.RoomRepository;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingDetailService {

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Utility utility;

    @Autowired
    private BookingDetailConverter bookingDetailConverter;

    @Transactional(rollbackFor = {Exception.class, DateTimeException.class, Throwable.class})
    public List<BookingDetail> createHotelBookingDetail(List<HotelBookingDetailRequest> requestList,
                                                        Booking bookingEntity) throws DateTimeException, Exception {

        if (bookingEntity != null) {
            List<BookingDetail> entities = new ArrayList<>();
            for (HotelBookingDetailRequest request : requestList) {
                Timestamp checkInDate = utility.formatDateTime(request.getCheckInDate().trim());
                Timestamp checkOutDate = utility.formatDateTime(request.getCheckOutDate().trim());
                Room roomEntity = roomRepository.getOne(request.getRoomID());
                BookingDetail bookingDetailEntity = new BookingDetail(request.getHotelName().trim(),
                        request.getRoomName().trim(), request.getRoomTypeName().trim(),
                        request.getRentalDays(), request.getRoomPrice(), request.getSubTotal(),
                        checkInDate, checkOutDate, roomEntity, bookingEntity);
                entities.add(bookingDetailEntity);
            }
            return bookingDetailRepository.saveAll(entities);
        }
        return null;
    }
}
