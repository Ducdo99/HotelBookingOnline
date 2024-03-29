package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "select r " +
            "from Room r " +
            "left join Hotel h on r.hotel.id = h.id " +
            "left join HotelAddress ha on h.id = ha.id " +
            "left join RoomType rt on r.roomType.id = rt.id " +
            "where r.roomStatus.name = :roomStatusName " +
            "and " +
            "( " +
            "h.name like %:hotelName% " +
            "or (ha.districtName like %:district% and ha.cityName like %:city%) " +
            ") " +
            "and r.id not in " +
            "( " +
            "select r.id " +
            "from Room r " +
            "left join BookingDetail bd on r.id = bd.room.id " +
            "left join Booking b on bd.booking.id = b.id " +
            "where b.bookingStatus.name = :bookingStatusName " +
            "and (bd.checkInDate >= :checkInDate or bd.checkOutDate <= :checkOutDate) " +
            ") " +
            "order by r.price asc ")
    public List<Room> findAllAvailableRoom(@Param("hotelName") String hotelName,
                                           @Param("district") String district,
                                           @Param("city") String city,
                                           @Param("checkInDate") Timestamp checkInDate,
                                           @Param("checkOutDate") Timestamp checkOutDate,
                                           @Param("roomStatusName") String roomStatusName,
                                           @Param("bookingStatusName") String bookingStatusName,
                                           Pageable pageable);

    @Query(value = "select count(r.id) " +
            "from Room r " +
            "left join Hotel h on r.hotel.id = h.id " +
            "left join HotelAddress ha on h.id = ha.id " +
            "left join RoomType rt on r.roomType.id = rt.id " +
            "where r.roomStatus.name like :roomStatusName " +
            "and " +
            "( " +
            "h.name like %:hotelName% " +
            "or (ha.districtName like %:district% and ha.cityName like %:city%) " +
            ") " +
            "and r.id not in " +
            "( " +
            "select r.id " +
            "from Room r " +
            "left join BookingDetail bd on r.id = bd.room.id " +
            "left join Booking b on bd.booking.id = b.id " +
            "where b.bookingStatus.name like :bookingStatusName " +
            "and (bd.checkInDate >= :checkInDate or bd.checkOutDate <= :checkOutDate) " +
            ") ")
    public Integer countAllAvailableRoom(@Param("hotelName") String hotelName,
                                         @Param("district") String district,
                                         @Param("city") String city,
                                         @Param("checkInDate") Timestamp checkInDate,
                                         @Param("checkOutDate") Timestamp checkOutDate,
                                         @Param("roomStatusName") String roomStatusName,
                                         @Param("bookingStatusName") String bookingStatusName);
}

