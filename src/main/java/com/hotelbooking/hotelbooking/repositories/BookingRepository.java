package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    public List<Booking> getAllByAccountIdAndBookingStatusNameOrderByBookingDate(Long accId, String bookingStatusName,
                                                                                 Pageable pageable);

    @Query(value = "select count(b.id) " +
            "from Booking b " +
            "where b.account.id = :accId " +
            "and b.bookingStatus.name like :bookingStatusName " +
            "and b.id in " +
            "( " +
            "select bd.booking.id " +
            "from BookingDetail bd " +
            "where bd.hotelName like %:hotelName% " +
            ")"
    )
    public Integer countByAccountIdAndBookingStatusNameAndHotelName(@Param("accId") Long accId,
                                                                    @Param("bookingStatusName") String bookingStatusName,
                                                                    @Param("hotelName") String hotelName);

    @Query(value = "select b " +
            "from Booking b " +
            "where b.account.id = :accId " +
            "and b.bookingStatus.name like :bookingStatusName " +
            "and b.id in " +
            "( " +
            "select bd.booking.id " +
            "from BookingDetail bd " +
            "where bd.hotelName like %:hotelName% " +
            ")"
    )
    public List<Booking> getByAccountIdAAndBookingStatusNameAndHotelName(@Param("accId") Long accId,
                                                                         @Param("bookingStatusName") String bookingStatusName,
                                                                         @Param("hotelName") String hotelName,
                                                                         Pageable pageable);

    public Integer countByAccountIdAndBookingStatusNameOrderByBookingDate(Long accId, String bookingStatusName);

    @Query(value = "select b " +
            "from Booking b " +
            "where b.account.id = :accId " +
            "and b.bookingStatus.name like :bookingStatusName " +
            "and b.bookingDate = :bookingDate"
    )

    public List<Booking> getAllByAccountIdAndBookingStatusNameAndBookingDate(@Param("accId") Long accId,
                                                                             @Param("bookingStatusName") String bookingStatusName,
                                                                             @Param("bookingDate") Timestamp bookingDate,
                                                                             Pageable pageable);

    public Integer countByAccountIdAndBookingStatusNameAndBookingDate(Long accId, String bookingStatusName,
                                                                      Timestamp bookingDate);

    public Booking getByAccountIdAndBookingStatusNameAndId(Long accId, String bookingStatusName, Long id);
}
