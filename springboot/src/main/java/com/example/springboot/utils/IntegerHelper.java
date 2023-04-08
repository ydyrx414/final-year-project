package com.example.springboot.utils;

public class IntegerHelper {
    public static boolean isValid(Integer integer){
        return integer != null && integer.intValue() > 0;
    }
}
