package com.hotelbooking.hotelbooking.converters;

import com.hotelbooking.hotelbooking.dtos.AccountDTO;
import com.hotelbooking.hotelbooking.entities.Account;
import com.hotelbooking.hotelbooking.entities.AccountStatus;
import com.hotelbooking.hotelbooking.entities.Role;
import com.hotelbooking.hotelbooking.inputs.AccountRegistrationRequest;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AccountConverter {
    public AccountDTO convertToAccountDTO(Account entity) {
        AccountDTO dto = new AccountDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail().trim());
        dto.setPwd(entity.getPwd().trim());
        dto.setFullName(entity.getFullName().trim());
        dto.setPhone(entity.getPhone());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setAddress(entity.getAddress().trim());
        dto.setIdCard(entity.getIdCard().trim());
        dto.setRoleID(entity.getRole().getId());
        dto.setAccountStatusID(entity.getAccountStatus().getId());
        return dto;
    }

    public Account convertToAccountEntity(AccountRegistrationRequest request, Role roleEntity,
                                          AccountStatus accountStatusEntity, Timestamp createdDate) {
        Account entity = new Account();
        entity.setEmail(request.getEmail().trim());
        entity.setPwd(request.getPwd().trim());
        entity.setFullName(request.getFullName().trim());
        entity.setPhone(request.getPhone());
        entity.setCreatedDate(createdDate);
        if (request.getAddress() != null) {
            entity.setAddress(request.getAddress().trim());
        }
        if (request.getIdCard() != null) {
            entity.setIdCard(request.getIdCard().trim());
        }
        entity.setRole(roleEntity);
        entity.setAccountStatus(accountStatusEntity);
        return entity;
    }
}
