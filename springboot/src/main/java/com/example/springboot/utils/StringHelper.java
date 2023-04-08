package com.example.springboot.utils;

public class StringHelper {
    public static boolean isBlankOrEmptyOrNull(String str){
        return str == null || str.length() == 0 || isBlank(str);
    }
    public static boolean isBlank(String str){
        for(char ch :str.toCharArray()){
            if(ch != ' '){
                return false;
            }
        }
        return true;
    }
}
