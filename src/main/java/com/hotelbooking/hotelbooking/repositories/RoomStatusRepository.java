package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
    public RoomStatus findByName(String name);
}
