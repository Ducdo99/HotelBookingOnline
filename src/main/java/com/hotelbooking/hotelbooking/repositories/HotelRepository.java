package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select h " +
            "from Hotel h " +
            "where h.id in " +
            "( " +
            "select r.hotel.id " +
            "from Room r " +
            "where r.roomStatus.name like :roomStatusName " +
            "group by r.hotel.id " +
            ")"
    )
    public List<Hotel> getAllByRoomStatusName(@Param("roomStatusName") String roomStatusName, Pageable pageable);

    @Query("select count(h.id) " +
            "from Hotel h " +
            "where h.id in " +
            "( " +
            "select r.hotel.id " +
            "from Room r " +
            "where r.roomStatus.name like :roomStatusName " +
            "group by r.hotel.id " +
            ")"
    )
    public Integer countHotelByRoomStatusName(@Param("roomStatusName") String roomStatusName);
}
