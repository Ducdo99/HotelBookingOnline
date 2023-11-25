package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.BookingDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {

    public List<BookingDetail> getAllByBookingId(Long id);

    @Query(value = "select bd " +
            "from BookingDetail bd " +
            "where bd.hotelName like %:hotelName% and bd.booking.id = :bookingId")
    public List<BookingDetail> getAllByHotelNameAndBookingId(@Param(value = "hotelName") String hotelName,
                                                             @Param(value = "bookingId") Long bookingId);

    public List<BookingDetail> getAllByBookingId(Long bookingId, Pageable pageable);

    public Integer countBookingDetailsByBookingId(Long bookingId);
}
