package com.justvastness.webchat.config.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.hutool.core.date.LocalDateTimeUtil.format;

public class LocalDateTimeUtil {
    private final static Locale EN_LOCAL = Locale.ENGLISH;

    private final static DateTimeFormatter DMYHMS = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", EN_LOCAL);
    private final static DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd-MMM-yyyy", EN_LOCAL);

    /**
     * 获取指定年份所有日期
     *
     * @param year {@link int} 年份
     * @return java.util.List<java.time.LocalDate>
     * @author wangdong
     * @date 2021/5/12 10:19 上午
     **/
    public static List<LocalDate> getAllDayByYear(int year) {
        var list = new ArrayList<LocalDate>();
        LocalDate firstDay = LocalDate.of(year, Month.of(1), 1);
        list.add(firstDay);
        while (true) {
            LocalDate localDate = firstDay.plusDays(1);
            list.add(localDate);
            firstDay = localDate;
            if (firstDay.with(TemporalAdjusters.lastDayOfYear()).compareTo(localDate) == 0) {
                break;
            }
        }
        return list;
    }

    /**
     * EN
     **/
    public static String enDmyhms() {
        return timeStr(LocalDateTime.now(), DMYHMS);
    }

    /**
     * EN
     **/
    public static String enDmyhms(LocalDate date, LocalTime time) {
        return enDmyhms(LocalDateTime.of(date, time));
    }


    /**
     * EN
     **/
    public static String enDmyhms(LocalDateTime date) {
        return timeStr(date, DMYHMS);
    }


    /**
     * EN
     **/
    public static String enDmy() {
        return enDmy(LocalDate.now());
    }

    /**
     * EN
     **/
    public static String enDmy(LocalDate localDate) {
        return timeStr(localDate, DMY);
    }

    public static String timeStr(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return format(localDateTime, dateTimeFormatter);
    }

    public static String timeStr(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {
        return format(localDate, dateTimeFormatter);
    }

    /**
     * 获取指定月份内所有日期
     *
     * @param year  {@link Integer} 年份
     * @param month {@link Integer} 月份
     * @return java.util.List<java.time.LocalDate>
     * @author wangdong
     * @date 2021/7/5 11:14 上午
     **/
    public static List<LocalDate> getAllDayOfMonth(Integer year, Integer month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end))
                .collect(Collectors.toList());
    }

    public static void main(String[] args){
        System.out.println(LocalDateTimeUtil.enDmy(LocalDate.now()));
    }
}
