package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.entities.Account;
import com.hotelbooking.hotelbooking.exceptions.NotFoundAccountException;
import com.hotelbooking.hotelbooking.ouputs.SuccessfulAuthenticationAccountResponse;
import com.hotelbooking.hotelbooking.repositories.AccountRepository;
import com.hotelbooking.hotelbooking.utils.JWTUtil;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JWTService implements UserDetailsService {
    // This class implements a UserDetailsService interface provided by Spring security.
    // This class overrides a single loadUserByUsername() method which will load by the user based on the values requested by the user.
    // The main function of this class authenticates a user information when an end user login

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private Utility utility;


    private void authenticateAccount(String email, String password)
            throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(email.trim(), password.trim());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    private List<SimpleGrantedAuthority> getAuthorityList(Long roleId) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(roleId).trim());
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email.trim());
        if (account == null) {
            throw new UsernameNotFoundException("Not found this account");
        }
        return new User(account.getEmail().trim(), account.getPwd().trim(), getAuthorityList(account.getRole().getId()));
    }

    public SuccessfulAuthenticationAccountResponse loginAccount(String email, String password)
            throws Exception {
        authenticateAccount(email, password);
        // load account
        Account accountInfo = accountRepository.findByEmail(email.trim());
        if (accountInfo == null) {
            throw new NotFoundAccountException("Not found this account");
        }
        if (!passwordEncoder.matches(password.trim(), accountInfo.getPwd().trim())) {
            throw new NotFoundAccountException("Not found this account");
        }// end if incorrect password
        Long roleID = accountInfo.getRole().getId();
        return new SuccessfulAuthenticationAccountResponse(accountInfo.getFullName().trim(),
                roleID,
                jwtUtil.createJWTString(accountInfo.getEmail().trim(), roleID));
    }
}
