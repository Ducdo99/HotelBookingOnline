package com.hotelbooking.hotelbooking.converters;

import com.hotelbooking.hotelbooking.dtos.HotelDTO;
import com.hotelbooking.hotelbooking.entities.Hotel;
import com.hotelbooking.hotelbooking.entities.HotelStatus;
import org.springframework.stereotype.Component;

@Component
public class HotelConverter {
    public HotelDTO convertToHotelDTO(Hotel entity) {
        HotelDTO dto = new HotelDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail().trim());
        dto.setName(entity.getName().trim());
        dto.setPhone(entity.getPhone());
        dto.setDescription(entity.getDescription());
        dto.setHotelStatusID(entity.getHotelStatus().getId());
        return dto;
    }

    public Hotel convertToHotelEntity(HotelDTO dto, HotelStatus hotelStatusEntity) {
        Hotel entity = new Hotel();
        entity.setEmail(dto.getEmail().trim());
        entity.setName(dto.getName().trim());
        entity.setPhone(dto.getPhone());
        entity.setDescription(dto.getDescription());
        entity.setHotelStatus(hotelStatusEntity);
        return entity;
    }
}
