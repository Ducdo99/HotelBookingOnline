package com.hotelbooking.hotelbooking.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class Utility {

    public Timestamp formatDateTime(String time) throws DateTimeException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MyConstantVariables.TIME_FORMATTING.trim());
        LocalDateTime localDateTime = LocalDateTime.parse(time.trim(), dateTimeFormatter);
        return Timestamp.valueOf(localDateTime);
    }

    public Timestamp getCurrentUTCTime() {
        Instant currentUTCTime = Instant.now();
        return this.formatDateTime(currentUTCTime.toString().trim());
    }

    public String bytesToHexString(byte[] hashingString) {
        StringBuilder hexString = new StringBuilder(2 * hashingString.length);
        for (int i = 0; i < hashingString.length; i++) {
            String hex = Integer.toHexString(0xff & hashingString[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }

    public String encryptPassword(String password, String algorithmName) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithmName.toString().trim());
        byte[] hashing = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return this.bytesToHexString(hashing);
    }

    public boolean validateInputString(String inputString, String regexString) {
        return Pattern.matches(regexString.trim(), inputString.trim());
    }

    public String convertDoubleValueToStringValue(Double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.toString().trim();
    }

    public String convertToDecimalFormat(String myPattern, Object digit)
            throws NullPointerException, IllegalArgumentException {
        DecimalFormat decimalFormat = new DecimalFormat(myPattern.trim());
        return decimalFormat.format(digit).trim();
    }
}
