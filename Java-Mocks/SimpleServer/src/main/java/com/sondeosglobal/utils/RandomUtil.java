package com.sondeosglobal.utils;


import java.util.Random;

public class RandomUtil {

    public static String getRandomElement(String[] list) {
        String response;
        response = list[getRamdomValue(list.length) - 1];
        return response;
    }

    public static int getRandomElement(int[] list) {
        int response;
        response = list[getRamdomValue(list.length) - 1];
        return response;
    }

    private static int getRamdomValue(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound) + 1;
    }
}
