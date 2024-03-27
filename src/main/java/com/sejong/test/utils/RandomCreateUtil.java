package com.sejong.test.utils;

import java.util.Random;

public class RandomCreateUtil {
    private final static Random random = new Random();
    public static String createRandomString() {
        // create random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";;
        int length = createRandomNumber(20,5);
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public static Integer createRandomNumber(int max, int min) {
        return random.nextInt((max - min) + 1) + min;
    }
}
