package com.springboot.spring.com;

public class IsNullCheck {
    public static boolean isNull(Object str){
        if(str == null || "".equals(str)){
            return true;
        }else{
            return false;
        }
    }
}
