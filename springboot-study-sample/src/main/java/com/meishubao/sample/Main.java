package com.meishubao.sample;

import cn.hutool.core.date.DatePattern;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lilu
 */
public class Main {

    public static void main(String[] args) {
        DateTime start = DateTime.parse("2022-10-13 07:24:01", DateTimeFormat.forPattern(DatePattern.NORM_DATETIME_PATTERN)),
                   end = DateTime.parse("2022-10-13 18:09:36", DateTimeFormat.forPattern(DatePattern.NORM_DATETIME_PATTERN));
        int days = Days.daysBetween(start, end).getDays();
        System.out.println(days);

        int minutes = Minutes.minutesBetween(start, end).getMinutes();
        double value = new BigDecimal(String.valueOf(minutes)).divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(value);

        start = DateTime.parse("2022-10-13 08:30:00", DateTimeFormat.forPattern(DatePattern.NORM_DATETIME_PATTERN));
        minutes = Minutes.minutesBetween(start, end).getMinutes();
        value = new BigDecimal(String.valueOf(minutes)).divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(value);
    }

}
