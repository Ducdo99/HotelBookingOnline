package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.BookingRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRequestStatusRepository extends JpaRepository<BookingRequestStatus, Long> {
    public BookingRequestStatus findByName(String name);
}
