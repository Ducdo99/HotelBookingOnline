package com.hotelbooking.hotelbooking.converters;

import com.hotelbooking.hotelbooking.dtos.RoomDTO;
import com.hotelbooking.hotelbooking.entities.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomConverter {

    public RoomDTO convertToRoomDTO(Room entity) {
        RoomDTO dto = new RoomDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setDay(entity.getDay());
        dto.setMaxPerson(entity.getMaxPerson());
        dto.setRoomTypeID(entity.getRoomType().getId());
        dto.setHotelID(entity.getHotel().getId());
        dto.setRoomStatusID(entity.getRoomStatus().getId());
        return dto;
    }

    public List<RoomDTO> covertToRoomDTOList(List<Room> entities) {
        List<RoomDTO> dtos = new ArrayList<>();
        for (Room entity : entities) {
            dtos.add(this.convertToRoomDTO(entity));
        }
        return dtos;
    }

    public Room convertToRoomEntity(RoomDTO dto) {
        Room entity = new Room(dto.getName().trim(), dto.getDescription().trim(),
                dto.getPrice(), dto.getDay(), dto.getMaxPerson());
        return entity;
    }

    public List<Room> convertToRoomEntityList(List<RoomDTO> dtos) {
        List<Room> entities = new ArrayList<>();
        for (RoomDTO dto : dtos
        ) {
            entities.add(this.convertToRoomEntity(dto));
        }
        return entities;
    }
}
