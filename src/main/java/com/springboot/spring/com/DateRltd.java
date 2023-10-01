package com.springboot.spring.com;

import java.util.Map;

public class DateRltd {

    public static String dateCnvrs(Map<String, Object> map){
        String str = "";
        switch (String.valueOf(map.get("className"))) {
            case "JANUARY":
                str = map.get("trnscdate")+"-01-01";
                break;
            case "FEBRUARY":
                //tDto.setTrnscdate(map.get("trnscdate")+"-02-01");
                str = map.get("trnscdate")+"-02-01";
                break;
            case "MARCH":
                //tDto.setTrnscdate(map.get("trnscdate")+"-03-01");
                str = map.get("trnscdate")+"-03-01";
                break;
            case "APRIL":
                //tDto.setTrnscdate(map.get("trnscdate")+"-04-01");
                str = map.get("trnscdate")+"-04-01";
                break;
            case "MAY":
                //tDto.setTrnscdate(map.get("trnscdate")+"-05-01");
                str = map.get("trnscdate")+"-05-01";
                break;
            case "JUNE":
                //tDto.setTrnscdate(map.get("trnscdate")+"-06-01");
                str = map.get("trnscdate")+"-06-01";
                break;
            case "JULY":
                //tDto.setTrnscdate(map.get("trnscdate")+"-07-01");
                str = map.get("trnscdate")+"-07-01";
                break;
            case "AUGUST":
                //tDto.setTrnscdate(map.get("trnscdate")+"-08-01");
                str = map.get("trnscdate")+"-08-01";
                break;
            case "SEPTEMBER":
                //tDto.setTrnscdate(map.get("trnscdate")+"-09-01");
                str = map.get("trnscdate")+"-09-01";
                break;
            case "OCTOBER":
                //tDto.setTrnscdate(map.get("trnscdate")+"-10-01");
                str = map.get("trnscdate")+"-10-01";
                break;
            case "NOVEMBER":
                //tDto.setTrnscdate(map.get("trnscdate")+"-11-01");
                str = map.get("trnscdate")+"-11-01";
                break;
            case "DECEMBER":
                //tDto.setTrnscdate(map.get("trnscdate")+"-12-01");
                str = map.get("trnscdate")+"-12-01";
                break;
        }
        return str;
    }
}
