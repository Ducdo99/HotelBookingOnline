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
public class FindingEmailUnitTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Utility myUtility;

    @Test
    public void FBE01() {
        String email = "";
        Assertions.assertNull(accountRepository.findByEmail(email));
    }

    @Test
    public void FBE02() {
        Assertions.assertNull(accountRepository.findByEmail(null));
    }

    @Test
    public void FBE03() {
        String existedEmail = "user@gmail.com";
        String fullName = "User";
        String phone = "0123456782";
        String address = "1/16/14 D2";
        String roleName = "User";
        String accSttName = "Active";
        String pwd = "User@123";
        Account account = accountRepository.findByEmail(existedEmail.trim());
        try {
            String encryptedPwd = myUtility.encryptPassword(pwd.trim(),
                    MyConstantVariables.PASSWORD_HASHING_NAME.trim());
            Assertions.assertEquals(existedEmail.trim(), account.getEmail());
            Assertions.assertEquals(encryptedPwd.trim(), account.getPwd());
            Assertions.assertEquals(fullName.trim(), account.getFullName());
            Assertions.assertEquals(phone.trim(), account.getPhone());
            Assertions.assertEquals(address.trim(), account.getAddress());
            Assertions.assertNull(null, account.getIdCard());
            Assertions.assertEquals(accSttName.trim(), account.getAccountStatus().getName());
            Assertions.assertEquals(roleName.trim(), account.getRole().getName());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void FBE04() {
        String email = "user1@gmail.com";
        Assertions.assertNull(accountRepository.findByEmail(email.trim()));
    }

    @Test
    public void FBE05() {
        String sqlInjection = "user@gmail.com; use master; DECLARE @DynSql nvarchar(max) = ''; " +
                "SELECT @DynSql += 'drop database '+sd.name+';' " +
                "FROM sys.databases sd " +
                "where sd.name not in ('master', 'tempdb', 'model', 'msdb') " +
                "EXEC(@DynSql);";
        Assertions.assertNull(accountRepository.findByEmail(sqlInjection.trim()));
    }

    @Test
    public void FBE06() {
        String email = "user@yahoo.com";
        Assertions.assertNull(accountRepository.findByEmail(email.trim()));
    }
}
