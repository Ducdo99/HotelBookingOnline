package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
