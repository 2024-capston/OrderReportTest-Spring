package com.sejong.test.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RandomCreateUtil {
    private final static Random random = new Random();
    private final static String[] categories = {"A", "B", "C"};
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

    public static BigDecimal createRandomRating(){
        return BigDecimal.valueOf(random.nextDouble() * 5).setScale(1, RoundingMode.HALF_UP);
    }

    public static Boolean createRandomDiscount(){
        return random.nextBoolean();
    }

    public static String createRandomCategories(){
        return categories[random.nextInt(categories.length)];
    }

}
