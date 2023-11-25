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
public class GuestFilter implements Filter {
    @Autowired
    private Utility utility;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        String uri = reqObj.getRequestURI();
        if (!uri.trim().startsWith("/guest/".trim())) {
            chain.doFilter(request, response);
        } else if (uri.trim().equalsIgnoreCase("/guest/register".trim())
                | uri.trim().equalsIgnoreCase("/guest/login".trim())) {
            chain.doFilter(request, response);
        } else {
            this.checkGuestRequest(request, response, chain, uri);
        }
    }

    private void checkGuestRequest(ServletRequest request, ServletResponse response, FilterChain chain, String uri)
            throws IOException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        HttpServletResponse resObj = (HttpServletResponse) response;
        Map<String, String> errors = new HashMap<>();
        try {
            String pageParam = reqObj.getParameter("page".trim());
            String pageSizeParam = reqObj.getParameter("pageSize".trim());
            // check current page parameter
            errors = utility.checkNumberError(pageParam, MyConstantVariables.NUMBER_FORMAT.trim(), errors, true,
                    "current page error", "Invalid current page format", "Invalid current page number");
            // check page size parameter
            errors = utility.checkNumberError(pageSizeParam, MyConstantVariables.NUMBER_FORMAT.trim(), errors, true,
                    "page size error", "Invalid page size format", "Invalid page size number");

            // check city id parameter
            if (uri.trim().startsWith("/guest/get-all-district/".trim())) {
                errors = this.checkDistrictSearchingParams(request, errors, uri);
            }
            // check district id parameter
            if (uri.trim().startsWith("/guest/get-all-ward/".trim())) {
                errors = this.checkWardSearchingParams(request, errors, uri);
            }
            // check room searching parameters
            if (uri.trim().startsWith("/guest/search-room".trim())) {
                errors = this.checkRoomSearchingParams(request, errors);
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

    private Map<String, String> checkDistrictSearchingParams(ServletRequest request, Map<String, String> errors,
                                                             String uri) {
        String digitStr = uri.trim().substring("/guest/get-all-district/".length());
        errors = utility.checkNumberError(digitStr, MyConstantVariables.NUMBER_FORMAT, errors,
                false, "city id error", "Invalid city id format",
                "Invalid city id number");
        return errors;
    }

    private Map<String, String> checkWardSearchingParams(ServletRequest request, Map<String, String> errors,
                                                         String uri) {
        String digitStr = uri.trim().substring("/guest/get-all-ward/".length());
        errors = utility.checkNumberError(digitStr, MyConstantVariables.NUMBER_FORMAT, errors,
                false, "district id error", "Invalid district id format",
                "Invalid district id number");
        return errors;
    }

    private Map<String, String> checkRoomSearchingParams(ServletRequest request, Map<String, String> errors) {
        String hotelName = request.getParameter("hotelName".trim());
        String district = request.getParameter("district".trim());
        String city = request.getParameter("city".trim().trim());
        String checkInDate = request.getParameter("checkInDate".trim());
        String checkOutDate = request.getParameter("checkOutDate".trim());
        errors = utility.checkStringError(hotelName, MyConstantVariables.HOTEL_NAME_FORMAT.trim(), errors,
                true, "hotel name error", "Invalid hotel name format");
        errors = utility.checkStringError(district, MyConstantVariables.DISTRICT_NAME_FORMAT.trim(), errors,
                false, "district name error", "Invalid district name format");
        errors = utility.checkStringError(city, MyConstantVariables.CITY_NAME_FORMAT.trim(), errors,
                false, "city error", "Invalid city name format");
        errors = utility.checkStringError(checkInDate, MyConstantVariables.BOOKING_DATE_FORMAT.trim(), errors,
                false, "check in date error", "Invalid check in date format");
        errors = utility.checkStringError(checkOutDate, MyConstantVariables.BOOKING_DATE_FORMAT.trim(), errors,
                false, "check out date error", "Invalid check out date format");
        return errors;
    }

}
