package com.hotelbooking.hotelbooking.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginRequestFilter implements Filter {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Utility utility;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        String uri = reqObj.getRequestURI();
        if (!uri.trim().equalsIgnoreCase("/guest/login".trim())) {
            chain.doFilter(request, response);
        } else {
            this.checkLoginRequest(request, response, chain);
        }
    }

    private void checkLoginRequest(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException {

        HttpServletRequest reqObj = (HttpServletRequest) request;
        HttpServletResponse resObj = (HttpServletResponse) response;
        String emailParam = request.getParameter("email".trim());
        String passwordParam = request.getParameter("password".trim());
        Map<String, String> errors = new HashMap<>();
        try {
            if (emailParam == null) {
                errors.put("email error", "Email can not null");
            } else {
                if (!utility.validateInputString(emailParam.trim(), MyConstantVariables.EMAIL_FORMAT.trim())) {
                    errors.put("email error", "Invalid email format");
                }
            }
            if (passwordParam == null) {
                errors.put("password error", "Password can not null");
            } else {
                if (!utility.validateInputString(passwordParam.trim(), MyConstantVariables.PASSWORD_FORMAT.trim())) {
                    errors.put("password error", "Invalid password format");
                }
            }
            if (errors.size() > 0) {
                resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException ex) {
            resObj.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errors.put("err_message", ex.getMessage());
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
