package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    public List<Ward> getAllByDistrictId(Long id);
}
