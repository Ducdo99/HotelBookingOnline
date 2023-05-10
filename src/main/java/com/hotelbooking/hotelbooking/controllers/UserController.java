package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.exceptions.NotFoundAccountException;
import com.hotelbooking.hotelbooking.exceptions.NotFoundDataException;
import com.hotelbooking.hotelbooking.inputs.HotelBookingCheckoutRequest;
import com.hotelbooking.hotelbooking.services.AccountService;
import com.hotelbooking.hotelbooking.services.BookingDetailService;
import com.hotelbooking.hotelbooking.services.BookingService;
import com.hotelbooking.hotelbooking.services.CouponOfAccountService;
import com.hotelbooking.hotelbooking.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.Collections;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CouponOfAccountService couponOfAccountService;

    @Autowired
    private BookingDetailService bookingDetailService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/account-information")
    public ResponseEntity<?> getAllUserInfo(HttpServletRequest request) {
        try {
            String jwtString = jwtUtil.getJWTStringFromHeader(request);
            return ResponseEntity.ok(accountService.getUserInfo(
                    jwtUtil.getEmailFromJWTString(jwtString.trim()), Long.valueOf(jwtUtil.getRoleID(jwtString.trim()))));
        } catch (NotFoundAccountException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NoSuchAlgorithmException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (SQLException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @PostMapping(value = "/hotel-booking-checkout")
    public ResponseEntity<?> createHotelBooking(
            @RequestBody(required = true) HotelBookingCheckoutRequest checkOutRequest,
            HttpServletRequest request) {
        try {
            String jwtString = jwtUtil.getJWTStringFromHeader(request);
            return ResponseEntity.ok(Collections.singletonMap(
                    "message", bookingService.creatHotelBooking(jwtUtil.getEmailFromJWTString(jwtString.trim()),
                            Long.valueOf(jwtUtil.getRoleID(jwtString.trim())), checkOutRequest)));
        } catch (NotFoundAccountException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (SQLException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (DateTimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @GetMapping(value = "/booking-history/{id}")
    public ResponseEntity<?> getDetailedBookingHistoryByBookingId(@PathVariable(name = "id") Long id,
                                                                  @RequestParam(name = "pageSize", required = false, defaultValue = "9") Integer pageSize,
                                                                  @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                                                  HttpServletRequest request) {
        try {
            String jwtString = jwtUtil.getJWTStringFromHeader(request);
            return ResponseEntity.ok(bookingService.getDetailedBookingHistoryByBookingId(pageSize, page, id,
                    jwtUtil.getEmailFromJWTString(jwtString.trim()), Long.valueOf(jwtUtil.getRoleID(jwtString.trim()))));
        } catch (NotFoundAccountException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NotFoundDataException ex) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @GetMapping(value = "/booking-history")
    public ResponseEntity<?> getAllBookingHistoryByAccountID(
            @RequestParam(name = "pageSize", required = false, defaultValue = "9") Integer pageSize,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            HttpServletRequest request) {

        try {
            String jwtString = jwtUtil.getJWTStringFromHeader(request);
            return ResponseEntity.ok(bookingService.getAllBookingHistoryByAccountID(pageSize, page,
                    jwtUtil.getEmailFromJWTString(jwtString.trim()), Long.valueOf(jwtUtil.getRoleID(jwtString.trim()))));
        } catch (NotFoundAccountException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NotFoundDataException ex) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @GetMapping(value = "/booking-history-by-hotel-name")
    public ResponseEntity<?> getAllBookingHistoryByHotelName(
            @RequestParam(name = "pageSize", required = false, defaultValue = "9") Integer pageSize,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "hotelName") String hotelName,
            HttpServletRequest request) {

        try {
            String jwtString = jwtUtil.getJWTStringFromHeader(request);
            return ResponseEntity.ok(bookingService.getAllBookingHistoryByHotelName(pageSize, page,
                    jwtUtil.getEmailFromJWTString(jwtString.trim()), Long.valueOf(jwtUtil.getRoleID(jwtString.trim())),
                    hotelName));
        } catch (NotFoundAccountException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NotFoundDataException ex) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @GetMapping(value = "/booking-history-by-booking-date")
    public ResponseEntity<?> getAllBookingHistoryByBookingDate(
            @RequestParam(name = "pageSize", required = false, defaultValue = "9") Integer pageSize,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "bookingDate") String bookingDate,
            HttpServletRequest request) {
        try {
            String jwtString = jwtUtil.getJWTStringFromHeader(request);
            return ResponseEntity.ok(bookingService.getAllBookingHistoryByBookingDate(pageSize, page,
                    jwtUtil.getEmailFromJWTString(jwtString.trim()), Long.valueOf(jwtUtil.getRoleID(jwtString.trim())),
                    bookingDate));
        } catch (DateTimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NotFoundAccountException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NotFoundDataException ex) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @GetMapping(value = "/coupons")
    public ResponseEntity<?> getAllUserCoupons(HttpServletRequest request) {
        try {
            String jwtString = jwtUtil.getJWTStringFromHeader(request);
            return ResponseEntity.ok(couponOfAccountService.getAllUserCouponsByAccountId(
                    jwtUtil.getEmailFromJWTString(jwtString.trim()), Long.valueOf(jwtUtil.getRoleID(jwtString.trim()))));
        } catch (NotFoundAccountException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NotFoundDataException ex) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

}
