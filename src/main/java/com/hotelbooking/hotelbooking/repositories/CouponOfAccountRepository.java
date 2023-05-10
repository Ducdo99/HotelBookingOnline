package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.entities.CouponOfAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponOfAccountRepository extends JpaRepository<CouponOfAccount, Long> {

    public List<CouponOfAccount> getAllByAccountIdAndCouponOfAccountStatusName(Long id,
                                                                               String couponOfAccountStatusName);
}
