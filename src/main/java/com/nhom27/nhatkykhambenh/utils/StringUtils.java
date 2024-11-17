package com.nhom27.nhatkykhambenh.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    public static String padWithZeros(String input, int length) {
        return String.format("%0" + length + "d", Integer.parseInt(input));
    }

    public String formatDate(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) return "N/A";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
}

