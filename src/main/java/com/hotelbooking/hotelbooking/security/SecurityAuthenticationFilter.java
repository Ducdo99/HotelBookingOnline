package com.hotelbooking.hotelbooking.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.hotelbooking.services.JWTService;
import com.hotelbooking.hotelbooking.utils.JWTUtil;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter
@Component
public class SecurityAuthenticationFilter extends OncePerRequestFilter {
    // This class is a custom filter, we will validate the Jwt token inside this class.
    // We will extend this class with OncePerRequestFilter provided by Spring security.

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private Utility utility;
    @Autowired
    private JWTService JWTService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        String uri = request.getRequestURI();
        try {
            if (uri.trim().startsWith("/guest/".trim())) {
                filterChain.doFilter(request, response);
            } else {
                // checking all request with user role, or admin role
                String reqHeader = request.getHeader("Authorization".trim());
                // get jwt string from Bearer string
                if (reqHeader != null) {
                    if (!reqHeader.isEmpty() & reqHeader.startsWith("Bearer ")) {
                        String jwtString = reqHeader.substring("Bearer ".length());
                        // check validation jwt string
                        if (jwtUtil.checkJWTExpirationTime(jwtString.trim())) {
                            String email = jwtUtil.getEmailFromJWTString(jwtString.trim());
                            String roleIDStr = jwtUtil.getRoleID(jwtString.trim());
                            if (utility.validateInputString(email.trim(), MyConstantVariables.EMAIL_FORMAT.trim())
                                    & utility.validateInputString(roleIDStr.trim(), MyConstantVariables.NUMBER_FORMAT.trim())) {
                                Long roleID = Long.valueOf(roleIDStr.trim());
                                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                                    // creating an empty SecurityContext
                                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                                    // Creating a new Authentication object
                                    // An UsernamePasswordAuthenticationToken object is also an Authentication object
                                    // Spring Security does not care what type of Authentication implementation is set on the SecurityContext
                                    UserDetails userDetails = JWTService.loadUserByUsername(email.trim());
                                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                            new UsernamePasswordAuthenticationToken(userDetails,
                                                    null, userDetails.getAuthorities());
                                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                    securityContext.setAuthentication(usernamePasswordAuthenticationToken);
                                }
                            } // end if role id is a number, and email string matches regex string
                            filterChain.doFilter(request, response);
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        errors.put("err_message", "The invalid token");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    errors.put("err_message", "The invalid request header");
                }
            }
        } catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errors.put("err_message", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errors.put("err_message", "This token expired");
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errors.put("err_message", ex.getMessage());
        } finally {
            if (errors.size() > 0) {
                response.setContentType("application/json");
                objectMapper.writeValue(response.getOutputStream(), errors);
            }
        }
    }
}
