package com.hotelbooking.hotelbooking.RepositoryTestCases.AccountRepositoryTestCases;

import com.hotelbooking.hotelbooking.HotelBookingApplication;
import com.hotelbooking.hotelbooking.entities.Account;
import com.hotelbooking.hotelbooking.repositories.AccountRepository;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Transactional
@SpringBootTest(classes = HotelBookingApplication.class)
public class FindingByEmailAndRoleIDTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Utility myUtility;

    @Test
    public void FBEARID01() {
        String email = "";
        Long roleID = null;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID02() {
        String email = null;
        Long roleID = null;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID03() {
        String email = "user";
        Long roleID = 1L;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID04() {
        String email = "user2@gmail.com";
        Long roleID = 1L;
        String fullName = "Second User";
        String phone = "0123456762";
        String address = "1/16/11 D5";
        String pwd = "User@123";
        String accSttName = "Active";
        Account account = accountRepository.findByEmailAndRoleID(email, roleID);
        try {
            String encryptedPwd = myUtility.encryptPassword(pwd.trim(), MyConstantVariables.PASSWORD_HASHING_NAME.trim());
            Assertions.assertEquals(email.trim(), account.getEmail());
            Assertions.assertEquals(encryptedPwd.trim(), account.getPwd());
            Assertions.assertEquals(fullName.trim(), account.getFullName());
            Assertions.assertEquals(phone.trim(), account.getPhone());
            Assertions.assertEquals(address.trim(), account.getAddress());
            Assertions.assertNull(null, account.getIdCard());
            Assertions.assertEquals(accSttName.trim(), account.getAccountStatus().getName());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void FBEARID05() {
        String email = "user1@yahoo.com";
        Long roleID = 1L;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID06() {
        String email = "user@gamil.com";
        Long roleID = 0L;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID07() {
        String email = "user@gamil.com";
        Long roleID = -1L;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID08() {
        String email = "user@gamil.com";
        Long roleID = 6L;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID09() {
        String email = "user@gmail.com\" OR 1=1";
        Long roleID = 1L;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }

    @Test
    public void FBEARID010() {
        String email = "user@gmail.com; use master; DECLARE @DynSql nvarchar(max) = ''; " +
                "SELECT @DynSql += 'drop database '+ sd.name +';'" +
                "FROM sys.databases sd " +
                "where sd.name not in ('master', 'tempdb', 'model', 'msdb') " +
                "EXEC(@DynSql); ";
        Long roleID = 1L;
        Assertions.assertNull(accountRepository.findByEmailAndRoleID(email, roleID));
    }
}
