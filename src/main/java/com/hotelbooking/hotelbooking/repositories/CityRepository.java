package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    public City getById(Long id);
}
