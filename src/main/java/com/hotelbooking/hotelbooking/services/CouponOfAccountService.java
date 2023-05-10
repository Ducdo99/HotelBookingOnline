package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.entities.Account;
import com.hotelbooking.hotelbooking.entities.CouponOfAccount;
import com.hotelbooking.hotelbooking.exceptions.NotFoundDataException;
import com.hotelbooking.hotelbooking.ouputs.CouponOfAccountInformationResponse;
import com.hotelbooking.hotelbooking.repositories.CouponOfAccountRepository;
import com.hotelbooking.hotelbooking.repositories.CouponOfAccountStatusRepository;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponOfAccountService {

    @Autowired
    private CouponOfAccountRepository couponOfAccountRepository;

    @Autowired
    private CouponOfAccountStatusRepository couponOfAccountStatusRepository;

    @Autowired
    private AccountService accountService;

    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public CouponOfAccount updateCouponRemainingQuantity(CouponOfAccount entity) throws Exception {
        entity.setRemainingQuantity(entity.getRemainingQuantity() > 0 ? entity.getRemainingQuantity() - 1 : 0);
        if (entity.getRemainingQuantity() <= 0) {
            entity.setCouponOfAccountStatus(couponOfAccountStatusRepository.findByName(
                    MyConstantVariables.UNAVAILABLE_COUPON_OF_ACCOUNT_STATUS_NAME.trim()));
        }
        return couponOfAccountRepository.save(entity);
    }

    public List<CouponOfAccountInformationResponse> getAllUserCouponsByAccountId(String email, Long roleID) {
        Account accountEntity = accountService.checkEmailAndRoleID(email.trim(), roleID);
        List<CouponOfAccount> accountCouponEntities = couponOfAccountRepository
                .getAllByAccountIdAndCouponOfAccountStatusName(accountEntity.getId(),
                        MyConstantVariables.AVAILABLE_COUPON_OF_ACCOUNT_STATUS_NAME.trim());
        if (accountCouponEntities != null) {
            if (!accountCouponEntities.isEmpty()) {
                List<CouponOfAccountInformationResponse> accountCouponList = new ArrayList<>();
                for (CouponOfAccount accountCoupon : accountCouponEntities) {
                    CouponOfAccountInformationResponse couponResponse = new CouponOfAccountInformationResponse(
                            accountCoupon.getId(), accountCoupon.getCouponCode().trim(),
                            accountCoupon.getDescription().trim(), accountCoupon.getRemainingQuantity(),
                            accountCoupon.getPercentage(), accountCoupon.getExpiryDate().toString().trim());
                    accountCouponList.add(couponResponse);
                }
                return accountCouponList;
            }
        }
        throw new NotFoundDataException("Not found any coupon");
    }
}
