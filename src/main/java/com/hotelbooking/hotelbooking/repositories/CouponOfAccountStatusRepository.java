package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.CouponOfAccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponOfAccountStatusRepository extends JpaRepository<CouponOfAccountStatus, Long> {
    public CouponOfAccountStatus findByName(String name);
}
