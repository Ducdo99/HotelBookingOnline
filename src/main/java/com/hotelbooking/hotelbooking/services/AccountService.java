package com.hotelbooking.hotelbooking.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.hotelbooking.hotelbooking.converters.AccountConverter;
import com.hotelbooking.hotelbooking.entities.Account;
import com.hotelbooking.hotelbooking.entities.AccountStatus;
import com.hotelbooking.hotelbooking.entities.Role;
import com.hotelbooking.hotelbooking.exceptions.ExistedAccountException;
import com.hotelbooking.hotelbooking.exceptions.NotFoundAccountException;
import com.hotelbooking.hotelbooking.exceptions.NotFoundDataException;
import com.hotelbooking.hotelbooking.inputs.AccountRegistrationRequest;
import com.hotelbooking.hotelbooking.ouputs.AccountInformationResponse;
import com.hotelbooking.hotelbooking.ouputs.SuccessfulAuthenticationAccountResponse;
import com.hotelbooking.hotelbooking.repositories.AccountRepository;
import com.hotelbooking.hotelbooking.repositories.AccountStatusRepository;
import com.hotelbooking.hotelbooking.repositories.RoleRepository;
import com.hotelbooking.hotelbooking.utils.JWTUtil;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.MyGoogleIdTokenVerifier;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private Utility utility;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MyGoogleIdTokenVerifier myGoogleIdTokenVerifier;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = {NumberFormatException.class, NoSuchAlgorithmException.class, DateTimeException.class,})
    public String createUserAccount(AccountRegistrationRequest accountInfo)
            throws NoSuchAlgorithmException, NumberFormatException, ExistedAccountException {
        String message = "fail registration";
        Account accountEntity = accountRepository.findByEmail(accountInfo.getEmail().trim());
        if (accountEntity != null) {
            throw new ExistedAccountException("The account existed");
        }

        Role roleEntity = roleRepository.findByName(MyConstantVariables.USER_ROLE_NAME.trim());
        AccountStatus accountStatusEntity = accountStatusRepository.findByName(
                MyConstantVariables.ACTIVE_ACCOUNT_STATUS_NAME.trim());

        Timestamp createdDate = utility.getCurrentUTCTime();

        // hashing password
        accountInfo.setPwd(passwordEncoder.encode(accountInfo.getPwd().trim()).trim());
        Account newAccount = accountConverter.convertToAccountEntity(
                accountInfo, roleEntity, accountStatusEntity, createdDate);
        Account result = accountRepository.save(newAccount);

        message = "success registration";
        return message;
    }

    public AccountInformationResponse getUserInfo(String email, Long roleID)
            throws SQLException, NoSuchAlgorithmException, NotFoundAccountException {
        AccountInformationResponse accountInformationResponse = null;
        Account accountInfo = this.checkEmailAndRoleID(email.trim(), roleID);
        accountInformationResponse = new AccountInformationResponse(accountInfo.getEmail(),
                accountInfo.getFullName(), accountInfo.getPhone(), accountInfo.getAddress(),
                accountInfo.getIdCard(), accountInfo.getRole().getId());

        return accountInformationResponse;
    }

    public Account checkEmailAndRoleID(String email, Long roleID) throws NotFoundAccountException {
        Account accountInformation = accountRepository.findByEmailAndRoleID(email.trim(), roleID);
        if (accountInformation == null) {
            throw new NotFoundAccountException("Not found this account");
        } else {
            return accountInformation;
        }
    }

    public SuccessfulAuthenticationAccountResponse loginUserAccountByGoogle(String idToken)
            throws GeneralSecurityException, IOException, NotFoundDataException {
        Payload payload = myGoogleIdTokenVerifier.getPayloadFromIDToken(idToken.trim());
        if (payload == null) {
            throw new NotFoundDataException("Failed login");
        }
        Account accountEntity = accountRepository.findByEmail(payload.getEmail().trim());
        if (accountEntity == null) {
            accountEntity = this.registerUserAccountByGoogle(payload);
        }
        Long roleID = accountEntity.getRole().getId();
        return new SuccessfulAuthenticationAccountResponse(
                accountEntity.getFullName().trim(), roleID,
                jwtUtil.createJWTString(accountEntity.getEmail().trim(), roleID));
    }

    @Transactional(rollbackFor = {DateTimeException.class})
    public Account registerUserAccountByGoogle(Payload payload) throws NoSuchAlgorithmException {
        Role roleEntity = roleRepository.findByName(MyConstantVariables.USER_ROLE_NAME.trim());
        AccountStatus accountStatusEntity = accountStatusRepository.findByName(
                MyConstantVariables.ACTIVE_ACCOUNT_STATUS_NAME.trim());
        Timestamp createdDate = utility.getCurrentUTCTime();

        Account newAccount = new Account();
        newAccount.setEmail(payload.getEmail().trim());
        // hashing password
        String hashedPassword = utility.encryptPassword("",
                MyConstantVariables.PASSWORD_HASHING_NAME.trim());
        newAccount.setPwd(hashedPassword.trim());
        newAccount.setFullName(payload.get("name".trim()).toString().trim());
        newAccount.setPhone("");
        newAccount.setCreatedDate(createdDate);
        newAccount.setAddress("");
        newAccount.setRole(roleEntity);
        newAccount.setAccountStatus(accountStatusEntity);
        return accountRepository.save(newAccount);
    }
}
