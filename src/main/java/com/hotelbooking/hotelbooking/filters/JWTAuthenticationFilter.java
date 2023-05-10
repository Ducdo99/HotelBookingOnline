package com.hotelbooking.hotelbooking.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.hotelbooking.entities.Account;
import com.hotelbooking.hotelbooking.repositories.AccountRepository;
import com.hotelbooking.hotelbooking.utils.JWTUtil;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = "/user/*")
public class JWTAuthenticationFilter implements Filter {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Utility utility;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        HttpServletResponse resObj = (HttpServletResponse) response;
        String reqHeader = reqObj.getHeader("Authorization".trim());
        Map<String, String> errors = new HashMap<>();
        try {
            // get jwt string from Bearer string
            if (reqHeader != null) {
                if (!reqHeader.trim().isEmpty() & reqHeader.contains("Bearer".trim())) {
                    String jwtString = reqHeader.substring("Bearer ".length());
                    // check validation jwt string
                    if (jwtUtil.checkJWTExpirationTime(jwtString.trim())) {
                        String email = jwtUtil.getEmailFromJWTString(jwtString.trim());
                        String roleIDStr = jwtUtil.getRoleID(jwtString.trim());
                        if (utility.validateInputString(email.trim(), MyConstantVariables.EMAIL_FORMAT.trim())
                                & utility.validateInputString(roleIDStr.trim(), MyConstantVariables.NUMBER_FORMAT.trim())) {
                            Long roleID = Long.valueOf(roleIDStr.trim());
                            Account accountEntity = accountRepository.findByEmailAndRoleID(email.trim(), roleID);
                            if (accountEntity == null) {
                                resObj.setStatus(HttpServletResponse.SC_NOT_FOUND);
                                errors.put("err_message", "Not found this user");
                            } else {
                                // The valid email, the valid role id, and the valid token existence time
                                chain.doFilter(request, response);
                            }
                        } // end if role id is a number, and email string matches regex string
                    }
                } else {
                    resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    errors.put("err_message", "The invalid token");
                }
            } else {
                resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                errors.put("err_message", "The invalid request header");
            }
        } catch (NumberFormatException ex) {
            resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errors.put("err_message", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errors.put("err_message", "This token expired");
        } catch (Exception ex) {
            resObj.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errors.put("err_message", ex.getMessage());
        } finally {
            if (errors.size() > 0) {
                resObj.setContentType("application/json");
                objectMapper.writeValue(resObj.getOutputStream(), errors);
            }
        }
    }
}
