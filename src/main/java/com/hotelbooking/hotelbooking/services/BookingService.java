package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.entities.Account;
import com.hotelbooking.hotelbooking.entities.Booking;
import com.hotelbooking.hotelbooking.entities.BookingDetail;
import com.hotelbooking.hotelbooking.entities.BookingRequestStatus;
import com.hotelbooking.hotelbooking.entities.BookingStatus;
import com.hotelbooking.hotelbooking.entities.CouponOfAccount;
import com.hotelbooking.hotelbooking.exceptions.NotFoundAccountException;
import com.hotelbooking.hotelbooking.exceptions.NotFoundDataException;
import com.hotelbooking.hotelbooking.inputs.HotelBookingCheckoutRequest;
import com.hotelbooking.hotelbooking.ouputs.BookingDetailHistoryListResponse;
import com.hotelbooking.hotelbooking.ouputs.BookingDetailHistoryResponse;
import com.hotelbooking.hotelbooking.ouputs.BookingHistoryListResponse;
import com.hotelbooking.hotelbooking.ouputs.BookingHistoryResponse;
import com.hotelbooking.hotelbooking.ouputs.DetailedBookingHistoryResponse;
import com.hotelbooking.hotelbooking.repositories.AccountRepository;
import com.hotelbooking.hotelbooking.repositories.BookingDetailRepository;
import com.hotelbooking.hotelbooking.repositories.BookingRepository;
import com.hotelbooking.hotelbooking.repositories.BookingRequestStatusRepository;
import com.hotelbooking.hotelbooking.repositories.BookingStatusRepository;
import com.hotelbooking.hotelbooking.repositories.CouponOfAccountRepository;
import com.hotelbooking.hotelbooking.repositories.RoomRepository;
import com.hotelbooking.hotelbooking.utils.MyConstantVariables;
import com.hotelbooking.hotelbooking.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private CouponOfAccountRepository couponOfAccountRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRequestStatusRepository bookingRequestStatusRepository;

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingDetailService bookingDetailService;

    @Autowired
    private CouponOfAccountService couponOfAccountService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private Utility utility;

    @Transactional(rollbackFor = {Exception.class, DateTimeException.class, Throwable.class})
    public String creatHotelBooking(String email, Long roleID, HotelBookingCheckoutRequest request)
            throws NotFoundAccountException, SQLException, DateTimeException, Exception {
        String checkoutMessage = "fail checkout";
        String roomStatusChangingErrorMessage = "failed";
        String couponRemainingQuantityUpdatingErrorMessage = "failed";
        // verify account
        Account accountEntity = accountService.checkEmailAndRoleID(email.trim(), roleID);
        CouponOfAccount couponOfAccountEntity = couponOfAccountRepository.getOne(request.getCouponOfAccountID());
        if (couponOfAccountEntity.getId() != null) {
            if(couponOfAccountEntity.getRemainingQuantity() > 0) {
                Timestamp bookingDate = utility.getCurrentUTCTime();
                BookingStatus bookingStatusEntity = bookingStatusRepository.findByName(
                        MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim());
                BookingRequestStatus bookingRequestStatusEntity = bookingRequestStatusRepository.findByName(
                        MyConstantVariables.REVERSED_BOOKING_REQUEST_STATUS_NAME.trim());
                Booking entity = new Booking(bookingDate, request.getTotal(), bookingStatusEntity, bookingRequestStatusEntity, accountEntity, couponOfAccountEntity, null);

                Booking bookingEntity = bookingRepository.save(entity);

                List<BookingDetail> bookingDetailEntities = bookingDetailService.createHotelBookingDetail(request.getHotelBookingDetailList(), bookingEntity);

                if (bookingDetailEntities != null) {
                    if (!bookingDetailEntities.isEmpty()) {
                        // change status room
                        for (BookingDetail bookingDetailEntity : bookingDetailEntities) {
                            roomStatusChangingErrorMessage = roomService.changeStatusOfRoom(bookingDetailEntity.getRoom(),
                                    MyConstantVariables.UNAVAILABLE_ROOM_STATUS_NAME.trim()) != null ? "succeeded" : "failed";
                        }
                        // update amount of coupon
                        couponOfAccountEntity = couponOfAccountService.updateCouponRemainingQuantity(couponOfAccountEntity);
                        if (couponOfAccountEntity != null) {
                            couponRemainingQuantityUpdatingErrorMessage = "succeeded";
                        }
                        if (roomStatusChangingErrorMessage.equalsIgnoreCase("succeeded".trim())
                                & couponRemainingQuantityUpdatingErrorMessage.trim().equalsIgnoreCase("succeeded".trim())) {
                            checkoutMessage = "success checkout";
                        }
                    }
                }
            }
        }
        return checkoutMessage;
    }

    public BookingHistoryListResponse getAllBookingHistoryByAccountID(int pageSize, int page, String email, Long roleID)
            throws NotFoundAccountException, NotFoundDataException {
        Account accountEntity = accountService.checkEmailAndRoleID(email.trim(), roleID);
        Integer totalBooking = bookingRepository.countByAccountIdAndBookingStatusNameOrderByBookingDate(
                accountEntity.getId(), MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim());

        if (totalBooking != null) {
            if (totalBooking > 0) {
                Integer totalPage = Double.valueOf(Math.ceil(
                        totalBooking.doubleValue() / Integer.valueOf(pageSize).doubleValue())).intValue();
                int pageIndex = page - 1;
                Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.unsorted());
                List<Booking> bookingEntities =
                        bookingRepository.getAllByAccountIdAndBookingStatusNameOrderByBookingDate(accountEntity.getId(),
                                MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), pageable);
                return this.createBookingHistoryListResponse(page, pageSize, totalPage, bookingEntities);
            }
        }
        throw new NotFoundDataException("Not found any booking");
    }


    public BookingHistoryListResponse getAllBookingHistoryByHotelName(
            int pageSize, int page, String email, Long roleID, String hotelName)
            throws NotFoundAccountException, NotFoundDataException {
        Account accountEntity = accountService.checkEmailAndRoleID(email.trim(), roleID);
        Integer totalBooking = bookingRepository.countByAccountIdAndBookingStatusNameAndHotelName(
                accountEntity.getId(), MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), hotelName.trim());

        if (totalBooking != null) {
            if (totalBooking > 0) {
                Integer totalPage = Double.valueOf(Math.ceil(
                        totalBooking.doubleValue() / Integer.valueOf(pageSize).doubleValue())).intValue();
                int pageIndex = page - 1;
                Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.unsorted());
                List<Booking> bookingEntities =
                        bookingRepository.getByAccountIdAAndBookingStatusNameAndHotelName(accountEntity.getId(),
                                MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), hotelName.trim(), pageable);
                return this.createBookingHistoryListResponse(page, pageSize, totalPage, bookingEntities);
            }
        }
        throw new NotFoundDataException("Not found any booking");
    }

    public BookingHistoryListResponse getAllBookingHistoryByBookingDate(int pageSize, int page, String email,
                                                                        Long roleID, String bookingDate)
            throws DateTimeException, NotFoundAccountException, NotFoundDataException {

        Account accountEntity = accountService.checkEmailAndRoleID(email.trim(), roleID);
        Timestamp convertedBookingDate = utility.formatDateTime(bookingDate.trim());
        Integer totalBooking = bookingRepository.countByAccountIdAndBookingStatusNameAndBookingDate(
                accountEntity.getId(), MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), convertedBookingDate);

        if (totalBooking != null) {
            if (totalBooking > 0) {
                Integer totalPage = Double.valueOf(Math.ceil(
                        totalBooking.doubleValue() / Integer.valueOf(pageSize).doubleValue())).intValue();
                int pageIndex = page - 1;
                Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.unsorted());
                List<Booking> bookingEntities =
                        bookingRepository.getAllByAccountIdAndBookingStatusNameAndBookingDate(accountEntity.getId(),
                                MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), convertedBookingDate, pageable);
                return this.createBookingHistoryListResponse(page, pageSize, totalPage, bookingEntities);
            }
        }
        throw new NotFoundDataException("Not found any booking");
    }

    private BookingHistoryListResponse createBookingHistoryListResponse(
            int page, int pageSize, int totalPage, List<Booking> bookingEntities)
            throws NotFoundDataException {
        if (bookingEntities != null) {
            List<BookingHistoryResponse> bookingHistoryResponseList = null;
            if (!bookingEntities.isEmpty()) {
                bookingHistoryResponseList = new ArrayList<>();
                for (Booking bookingEntity : bookingEntities) {
                    BookingHistoryResponse bookingHistoryResponse = new BookingHistoryResponse();
                    bookingHistoryResponse.setBookingID(bookingEntity.getId());
                    bookingHistoryResponse.setBookingDate(bookingEntity.getBookingDate().toString().trim());
                    bookingHistoryResponse.setTotal(utility.convertToDecimalFormat(
                            MyConstantVariables.THOUSANDS_AND_THOUSANDTHS_UNIT.trim(), bookingEntity.getTotal()));
                    bookingHistoryResponse.setCouponCodeOfTheAccount(
                            bookingEntity.getCouponOfAccount().getCoupon().getCouponCode());
                    bookingHistoryResponse.setBookingRequestStatusName(
                            bookingEntity.getBookingRequestStatus().getName());
                    bookingHistoryResponseList.add(bookingHistoryResponse);
                }
            }
            if (bookingHistoryResponseList != null) {
                BookingHistoryListResponse bookingHistoryListResponse = new BookingHistoryListResponse();
                bookingHistoryListResponse.setTotalPage(totalPage);
                bookingHistoryListResponse.setCurrentPage(page);
                bookingHistoryListResponse.setPreviousPage(page - 1 < 1 ? 1 : page - 1);
                bookingHistoryListResponse.setNextPage(page + 1 > totalPage ? totalPage : page + 1);
                bookingHistoryListResponse.setPageSize(pageSize);
                bookingHistoryListResponse.setBookingHistoryList(bookingHistoryResponseList);
                return bookingHistoryListResponse;
            }
        }
        throw new NotFoundDataException("Not found any booking");
    }

    public DetailedBookingHistoryResponse getDetailedBookingHistoryByBookingId(int pageSize, int page, Long id,
                                                                               String email, Long roleID)
            throws NotFoundAccountException, NotFoundDataException {
        Account accountEntity = accountService.checkEmailAndRoleID(email.trim(), roleID);
        Booking bookingEntity = bookingRepository.getByAccountIdAndBookingStatusNameAndId(accountEntity.getId(),
                MyConstantVariables.AVAILABLE_BOOKING_STATUS_NAME.trim(), id);

        if (bookingEntity != null) {
            Integer totalBookingDetail = bookingDetailRepository.countBookingDetailsByBookingId(id);
            if (totalBookingDetail != null) {
                if (totalBookingDetail > 0) {
                    Integer totalPage = Double.valueOf(Math.ceil(
                            totalBookingDetail.doubleValue() / Integer.valueOf(pageSize).doubleValue())).intValue();
                    int pageIndex = page - 1;
                    Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.unsorted());
                    List<BookingDetail> bookingDetailEntities =
                            bookingDetailRepository.getAllByBookingId(id, pageable);
                    if (bookingDetailEntities != null) {
                        if (!bookingDetailEntities.isEmpty()) {
                            DetailedBookingHistoryResponse detailedBookingHistoryResponse =
                                    new DetailedBookingHistoryResponse();
                            detailedBookingHistoryResponse.setBookingID(bookingEntity.getId());
                            detailedBookingHistoryResponse.setBookingDate(bookingEntity.getBookingDate().toString().trim());
                            detailedBookingHistoryResponse.setTotal(utility.convertToDecimalFormat(
                                    MyConstantVariables.THOUSANDS_AND_THOUSANDTHS_UNIT.trim(),
                                    bookingEntity.getTotal()).trim());
                            detailedBookingHistoryResponse.setCouponCodeOfTheAccount(
                                    bookingEntity.getCouponOfAccount().getCoupon().getCouponCode());
                            detailedBookingHistoryResponse.setBookingRequestStatusName(
                                    bookingEntity.getBookingRequestStatus().getName());
                            detailedBookingHistoryResponse.setBookingDetailHistory(
                                    this.createBookingDetailHistoryListResponse(page, pageSize, totalPage,
                                            bookingDetailEntities));
                            return detailedBookingHistoryResponse;
                        }
                    }
                }
            }
        }
        throw new NotFoundDataException("Not found any booking");
    }

    private BookingDetailHistoryListResponse createBookingDetailHistoryListResponse(
            int page, int pageSize, int totalPage, List<BookingDetail> bookingDetailEntities) {
        if (bookingDetailEntities != null) {
            List<BookingDetailHistoryResponse> bookingDetailHistoryResponseList = null;
            if (!bookingDetailEntities.isEmpty()) {
                bookingDetailHistoryResponseList = new ArrayList<>();
                for (BookingDetail bookingDetailEntity : bookingDetailEntities) {
                    BookingDetailHistoryResponse bookingDetailHistoryResponse = new BookingDetailHistoryResponse();
                    bookingDetailHistoryResponse.setHotelName(bookingDetailEntity.getHotelName());
                    bookingDetailHistoryResponse.setRoomName(bookingDetailEntity.getRoomName());
                    bookingDetailHistoryResponse.setRoomType(bookingDetailEntity.getRoomType());
                    bookingDetailHistoryResponse.setDays(bookingDetailEntity.getDays());
                    bookingDetailHistoryResponse.setPrice(utility.convertToDecimalFormat(
                            MyConstantVariables.THOUSANDS_AND_THOUSANDTHS_UNIT.trim(), bookingDetailEntity.getPrice()));
                    bookingDetailHistoryResponse.setSubTotal(utility.convertToDecimalFormat(
                            MyConstantVariables.THOUSANDS_AND_THOUSANDTHS_UNIT.trim(),
                            bookingDetailEntity.getSubTotal()));
                    bookingDetailHistoryResponse.setCheckInDate(
                            bookingDetailEntity.getCheckInDate().toString().trim());
                    bookingDetailHistoryResponse.setCheckOutDate(
                            bookingDetailEntity.getCheckOutDate().toString().trim());
                    bookingDetailHistoryResponseList.add(bookingDetailHistoryResponse);
                }
            }
            if (bookingDetailHistoryResponseList != null) {
                BookingDetailHistoryListResponse bookingDetailHistoryListResponse =
                        new BookingDetailHistoryListResponse();
                bookingDetailHistoryListResponse.setTotalPage(totalPage);
                bookingDetailHistoryListResponse.setCurrentPage(page);
                bookingDetailHistoryListResponse.setPreviousPage(page - 1 < 1 ? 1 : page - 1);
                bookingDetailHistoryListResponse.setNextPage(page + 1 > totalPage ? totalPage : page + 1);
                bookingDetailHistoryListResponse.setPageSize(pageSize);
                bookingDetailHistoryListResponse.setBookingDetailHistoryList(bookingDetailHistoryResponseList);
                return bookingDetailHistoryListResponse;
            }
        }
        throw new NotFoundDataException("Not found any booking detail");
    }
}