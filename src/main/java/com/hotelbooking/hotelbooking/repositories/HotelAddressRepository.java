package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.HotelAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelAddressRepository extends JpaRepository<HotelAddress, Long> {
}
