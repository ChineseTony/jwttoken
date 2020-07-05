package com.tom.jwttoken.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil(){

    }

    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

    private static SimpleDateFormat getDateFormat() {
        SimpleDateFormat dateFormat = local.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            local.set(dateFormat);
        }
        return dateFormat;
    }

    public static String format(Date date) {
        return getDateFormat().format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        return getDateFormat().parse(dateStr);
    }
}
