package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.exceptions.ExistedAccountException;
import com.hotelbooking.hotelbooking.exceptions.NotFoundAccountException;
import com.hotelbooking.hotelbooking.exceptions.NotFoundDataException;
import com.hotelbooking.hotelbooking.inputs.AccountRegistrationRequest;
import com.hotelbooking.hotelbooking.services.AccountService;
import com.hotelbooking.hotelbooking.services.CityService;
import com.hotelbooking.hotelbooking.services.DistrictService;
import com.hotelbooking.hotelbooking.services.HotelService;
import com.hotelbooking.hotelbooking.services.RoomService;
import com.hotelbooking.hotelbooking.services.WardService;
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

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Collections;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/get-all-hotel")
    public ResponseEntity<?> searchHotelsByAvailableRoomStatus(
            @RequestParam(name = "pageSize", required = false, defaultValue = "9") Integer pageSize,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
        try {
            return ResponseEntity.ok(hotelService.getAllHotelAvailable(pageSize, page));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @GetMapping(value = "/search-room")
    public ResponseEntity<?> searchAvailableRooms(
            @RequestParam(name = "hotelName", required = false, defaultValue = "") String hotelName,
            @RequestParam(name = "district") String district,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "checkInDate") String checkInDate,
            @RequestParam(name = "checkOutDate") String checkOutDate,
            @RequestParam(name = "pageSize", required = false, defaultValue = "9") Integer pageSize,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
        try {
            return ResponseEntity.ok(roomService.getAvailableRoomsInfo(
                    hotelName, district, city, checkInDate, checkOutDate, page, pageSize));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @GetMapping(value = "/get-all-city")
    public ResponseEntity<?> getAllCity() {
        return ResponseEntity.ok(cityService.getAllCity());
    }

    @GetMapping(value = "/get-all-district/{id}")
    public ResponseEntity<?> getAllDistrict(@PathVariable(name = "id") Long cityID) {
        return ResponseEntity.ok(districtService.getAllDistrictByCityID(cityID));
    }

    @GetMapping(value = "/get-all-ward/{id}")
    public ResponseEntity<?> getAllWard(@PathVariable(name = "id") Long districtID) {
        return ResponseEntity.ok(wardService.getAllWardByDistrictID(districtID));
    }

    @PostMapping(value = "/login-by-google-account")
    public ResponseEntity<?> loginByGoogleAccount(@RequestParam(name = "idToken") String idToken) {
        try {
            return ResponseEntity.ok(accountService.loginUserAccountByGoogle(idToken.trim()));
        } catch (NotFoundDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginAccount(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password) {
        try {
            return ResponseEntity.ok(accountService.loginAccount(email, password));
        } catch (NotFoundAccountException ex) {
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

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerAccount(
            @RequestBody(required = true) AccountRegistrationRequest accountRegistrationRequest) {
        try {
            return ResponseEntity.ok(Collections.singletonMap(
                    "message", accountService.createUserAccount(accountRegistrationRequest)));
        } catch (ExistedAccountException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (NoSuchAlgorithmException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("err_message", ex.getMessage()));
        }
    }
}
