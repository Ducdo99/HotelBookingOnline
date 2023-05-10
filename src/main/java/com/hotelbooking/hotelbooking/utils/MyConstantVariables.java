package com.hotelbooking.hotelbooking.utils;

public class MyConstantVariables {
    /* The constant variables relate to a validation string */
    public static final String EMAIL_FORMAT = "^[\\w-\\.]+@gmail.com$";
    public static final String PASSWORD_FORMAT = "^" + // start checking
            "(?=.*?[\\p{Lu}])" + // check at least one upper case letter
            "(?=.*?[\\p{Ll}])" + // check at least one lower case letter
            "(?=.*?[\\d])" + // check at least one number letter
            "(?=.*?[\\p{S}\\p{Po}\\p{Pc}\\p{Ps}\\p{Pe}\\p{Pd}])" + // check at least one special character letter
            "[\\p{Lu}\\p{Ll}\\d\\p{S}\\p{Po}\\p{Pc}\\p{Ps}\\p{Pe}\\p{Pd}]{8,250}" + // total character at least 8 character
            "$"; // end checking
    public static final String FULL_NAME_FORMAT = "[\\p{L}\\s]{2,250}";
    public static final String CITY_NAME_FORMAT = "[\\p{L}\\s.]{2,250}";
    public static final String CELLPHONE_FORMAT = "\\d{10}";
    public static final String ADDRESS_FORMAT = "[\\p{L}\\d\\s\\-\\/,]{1,75}";
    public static final String ID_CARD_FORMAT = "\\d{12}";
    public static final String HOTEL_NAME_FORMAT = "[\\p{L}\\d\\s]{2,250}";
    public static final String FROM_BOOKING_DATE_FORMAT = "[\\d\\/:-]{2,}";
    public static final String TO_BOOKING_DATE_FORMAT = "[\\d\\/:-]{2,}";
    public static final String NUMBER_FORMAT = "\\d{1,}";

    /* The constant variables relate to database */
    public static final String AVAILABLE_BOOKING_STATUS_NAME = "Available";
    public static final String REVERSED_BOOKING_REQUEST_STATUS_NAME = "Reversed";
    public static final String UNAVAILABLE_ROOM_STATUS_NAME = "Unavailable";
    public static final String AVAILABLE_ROOM_STATUS_NAME = "Available";
    public static final String AVAILABLE_COUPON_OF_ACCOUNT_STATUS_NAME = "Available";
    public static final String UNAVAILABLE_COUPON_OF_ACCOUNT_STATUS_NAME = "Unavailable";
    public static final String USER_ROLE_NAME = "User";
    public static final String ACTIVE_ACCOUNT_STATUS_NAME = "Active";

    /* The constant variables relate to formatting string */
    public static final String THOUSANDS_AND_THOUSANDTHS_UNIT = "#000.000";

    public static final String PASSWORD_HASHING_NAME = "";
    public static final long EXPIRATION_TIME = (60 * 60 * 1) / 2; // 30 minutes
    public static final String TIME_FORMATTING = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

}
