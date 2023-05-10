package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatus, Long> {
    public BookingStatus findByName(String name);

}
