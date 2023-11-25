package com.hotelbooking.hotelbooking.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class UserFilter implements Filter {

    @Autowired
    private Utility utility;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        String uri = reqObj.getRequestURI().trim();
        if (!uri.trim().startsWith("/user/".trim())) {
            chain.doFilter(request, response);
        } else {
            this.checkUserRequests(request, response, chain, uri);
        }
    }

    private void checkUserRequests(ServletRequest request, ServletResponse response, FilterChain chain, String uri)
            throws IOException {
        HttpServletRequest reqObj = (HttpServletRequest) request;
        HttpServletResponse resObj = (HttpServletResponse) response;
        Map<String, String> errors = new HashMap<>();
        try {
            String currentPageParam = reqObj.getParameter("page".trim());
            String pageSizeParam = reqObj.getParameter("pageSize".trim());
            // check current page parameter
            errors = utility.checkNumberError(currentPageParam, MyConstantVariables.NUMBER_FORMAT.trim(), errors, true,
                    "current page error", "Invalid current page format", "Invalid current page number");
            // check page size parameter
            errors = utility.checkNumberError(pageSizeParam, MyConstantVariables.NUMBER_FORMAT.trim(), errors, true,
                    "page size error", "Invalid page size format", "Invalid page size number");

            // check hotel name parameter
            if (uri.trim().startsWith("/user/booking-history-by-hotel-name".trim())) {
                errors = this.checkBookingHistoryByHotelNameParams(request, errors);
            }
            // check booking date parameter
            if (uri.trim().startsWith("/user/booking-history-by-booking-date".trim())) {
                errors = this.checkBookingHistoryByBookingDateParams(request, errors);
            }

            if (!errors.isEmpty()) {
                resObj.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                chain.doFilter(request, response);
            }
        } catch (ServletException ex) {
            resObj.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errors.put("User_filter_err_message", ex.getMessage());

        } catch (IOException ex) {
            resObj.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errors.put("User_filter_err_message", ex.getMessage());
        } finally {
            if (!errors.isEmpty()) {
                resObj.setContentType(MediaType.APPLICATION_JSON_VALUE);
                objectMapper.writeValue(resObj.getOutputStream(), errors);
            }
        }
    }


    private Map<String, String> checkBookingHistoryByHotelNameParams(ServletRequest request,
                                                                     Map<String, String> errors) {
        String hotelName = request.getParameter("hotelName".trim());
        errors = utility.checkStringError(hotelName, MyConstantVariables.HOTEL_NAME_FORMAT.trim(), errors,
                true, "hotel name error", "Invalid hotel name format");
        return errors;
    }

    private Map<String, String> checkBookingHistoryByBookingDateParams(ServletRequest request,
                                                                       Map<String, String> errors) {
        String bookingDate = request.getParameter("bookingDate".trim());
        errors = utility.checkStringError(bookingDate, MyConstantVariables.BOOKING_DATE_FORMAT.trim(), errors,
                false, "booking date error", "Invalid booking date format");
        return errors;
    }
}
