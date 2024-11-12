package com.nhom27.nhatkykhambenh.utils;

public class StringUtils {
    public static String padWithZeros(String input, int length) {
        return String.format("%0" + length + "d", Integer.parseInt(input));
    }
}

