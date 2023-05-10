package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    public District getById(Long id);

    public List<District> getAllByCityId(Long id);
}
