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
    public static final String CITY_NAME_FORMAT = "[\\p{L}\\ .]{2,250}";
    public static final String CELLPHONE_FORMAT = "\\d{10}";
    public static final String ADDRESS_FORMAT = "[\\p{L}\\d\\s\\-\\/,]{1,75}";
    public static final String ID_CARD_FORMAT = "\\d{12}";
    public static final String HOTEL_NAME_FORMAT = "[\\p{L}\\d\\s]{2,250}";
    public static final String DISTRICT_NAME_FORMAT = "[\\p{L}\\d\\s]{2,250}";
    public static final String BOOKING_DATE_FORMAT =
            "(19[0-9]{2}|[2-9]{1}[0-9]{3})" + // year formatting: 1900 - 9999
                    "[-]" + // the symbol between year and month
                    "(0[1-9]|1[012])" + // month formatting: 01 - 12
                    "[-]" + // the symbol between month and day
                    "(0[1-9]|[12][0-9]|3[01])" + // day formatting: 01 - 31. [12][0-9] => 1[0-9]|2[0-9]
                    "[ ]" + // a white space separating date and time
                    "([01][0-9]|2[0-3])" + // hours formatting: 00 - 23
                    "[:]" + // a symbol between hours and minutes
                    "([0-5][0-9])" + // minutes formatting: 00 - 59
                    "[:]" + // the symbol between minutes and seconds
                    "([0-5][0-9])" + // seconds formatting: 00 - 59
                    "[.]" + // the symbol between minutes and milliseconds
                    "\\d{3,}";
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

    public static final String PASSWORD_HASHING_NAME = "SHA-256";
    public static final long EXPIRATION_TIME = (60 * 60 * 1) / 2; // 30 minutes
    public static final String TIME_FORMATTING = "yyyy-MM-dd HH:mm:ss.SSS";

}
