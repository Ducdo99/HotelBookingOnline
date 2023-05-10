package com.hotelbooking.hotelbooking.converters;

import com.hotelbooking.hotelbooking.dtos.BookingDetailDTO;
import com.hotelbooking.hotelbooking.entities.BookingDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDetailConverter {
    public BookingDetailDTO convertToBookingDetailDTO(BookingDetail entity) {
        BookingDetailDTO dto = new BookingDetailDTO();
        dto.setId(entity.getId());
        dto.setHotelName(entity.getHotelName());
        dto.setRoomName(entity.getRoomName());
        dto.setRoomType(entity.getRoomType());
        dto.setDays(entity.getDays());
        dto.setPrice(entity.getPrice());
        dto.setSubTotal(entity.getSubTotal());
        dto.setCheckInDate(entity.getCheckInDate());
        dto.setCheckOutDate(entity.getCheckOutDate());
        dto.setBookingID(entity.getBooking().getId());
        dto.setRoomID(entity.getRoom().getId());
        return dto;
    }

    public List<BookingDetailDTO> convertToBookingDetailDTOList(List<BookingDetail> entities) {
        List<BookingDetailDTO> bookingDetailDTOList = new ArrayList<>();
        for (BookingDetail entity : entities) {
            bookingDetailDTOList.add(this.convertToBookingDetailDTO(entity));
        }
        return bookingDetailDTOList;
    }
}
