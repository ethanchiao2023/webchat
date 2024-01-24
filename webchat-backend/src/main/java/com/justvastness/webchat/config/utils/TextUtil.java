package com.justvastness.webchat.config.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.justvastness.webchat.config.constants.FormatConstants;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static cn.hutool.core.text.CharSequenceUtil.*;
import static cn.hutool.core.text.StrPool.CRLF;

@Slf4j
public class TextUtil {
    private static final String NA_DATE = "99991231,00010101";
    private static final String NA_TIME = "00:00,23:59";

    /**
     * 分割字符串
     * <p>
     * 以 "\r\n" 分割
     * </p>
     *
     * @param str {@link String} 字符串
     * @return java.util.List<java.lang.String>
     * @author wangdong
     * @date 2021/11/12 2:21 下午
     **/
    public static List<String> splitCrlf(String str) {
        return StrUtil.split(str, CRLF);
    }

    /**
     * 单条数据时
     * <p>
     * 筛选指定标识开头的数据
     * 并且从原数组中删除该数据
     *
     * @param list     {@link List} 原数组
     * @param startStr {@link String} 标识
     * @return java.util.List<java.lang.String>
     * @author wangdong
     * @date 2021/11/12 2:30 下午
     **/
    public static String filterAnyStartWith(List<String> list, String startStr) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            if (StrUtil.startWith(next, startStr)) {
                iterator.remove();
                return next;
            }
        }
        return null;
    }

    /**
     * 多条数据时
     * <p>
     * 筛选指定标识开头的数据
     * 并且从原数组中删除该数据
     *
     * @param list     {@link List} 原数组
     * @param startStr {@link String} 标识
     * @return java.util.List<java.lang.String>
     * @author wangdong
     * @date 2021/11/12 2:30 下午
     **/
    public static List<String> filterStartWith(List<String> list, String startStr) {
        List<String> result = new ArrayList<>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            if (StrUtil.startWith(next, startStr)) {
                result.add(next);
                iterator.remove();
            }
        }
        return result;
    }

    /**
     * 转 BigDecimal
     *
     * @param str    {@link String} 原字符串
     * @param offset {@link } 整数位数
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/12 2:50 下午
     **/
    public static BigDecimal toBigDecimal(String str, int offset) {
        final String trim = trim(str);
        if (isBlank(str) || str.length() <= 0) {
            return BigDecimal.ZERO;
        }
        final StringBuilder insert = new StringBuilder(trim).insert(offset, ".");
        return NumberUtil.toBigDecimal(insert.toString());
    }

    public static BigDecimal toBigDecimal(String str,int start,int end, int offset) {
        final String trim = trim(sub(str,start,end));
        if (isBlank(str) || str.length() <= 0) {
            return BigDecimal.ZERO;
        }
        final StringBuilder insert = new StringBuilder(trim).insert(offset, ".");
        return NumberUtil.toBigDecimal(insert.toString());
    }

    /**
     * 转 LocalDate
     *
     * @param str {@link String} 原字符串
     * @return java.time.LocalDate
     * @author wangdong
     * @date 2021/11/12 2:53 下午
     **/
    public static LocalDate toLocalDate(String str) {
        return LocalDate.parse(str, FormatConstants.DATE_FORMATTER_YMD);
    }

    /**
     * 截取指定长度，转换成LocalDate
     *
     * @param str {@link }
     * @param start {@link }
     * @param end {@link }
     * @return java.time.LocalDate
     * @author D.J
     * @date 2021/11/22 10:45 AM
     **/
    public static LocalDate toLocalDate(String str,int start,int end) {
        if (Arrays.asList(NA_DATE.split(",")).contains(sub(str,start,end))) {
            return null;
        }
        return LocalDate.parse(sub(str,start,end), FormatConstants.DATE_FORMATTER_YMD);
    }

    /**
     * 转 LocalDate 跳过 99991231,00010101
     *
     * @param str {@link String} 原字符串
     * @return java.time.LocalDate
     * @author wangdong
     * @date 2021/11/12 2:53 下午
     **/
    public static LocalDate toLocalDateHandle99991231(String str) {
        if (Arrays.asList(NA_DATE.split(",")).contains(str)) {
            return null;
        }
        return LocalDate.parse(str, FormatConstants.DATE_FORMATTER_YMD);
    }

    /**
     * 转 LocalTime
     *
     * @param str {@link String} 原字符串
     * @return java.time.LocalTime
     * @author wangdong
     * @date 2021/11/12 2:53 下午
     **/
    public static LocalTime toLocalTime(String str) {
        return LocalTime.parse(str, FormatConstants.DATE_FORMATTER_HM);
    }

    public static LocalTime toLocalTime2(String str) {
        return LocalTime.parse(str, FormatConstants.DATE_FORMATTER_HHMM);
    }

    /**
     * 截取指定长度，转换成LocalTime
     *
     * @param str {@link }
     * @param start {@link }
     * @param end {@link }
     * @return java.time.LocalTime
     * @author D.J
     * @date 2021/11/22 10:46 AM
     **/
    public static LocalTime toLocalTime(String str,int start,int end) {
        if (Arrays.asList(NA_TIME.split(",")).contains(sub(str,start,end))) {
            return null;
        }
        return LocalTime.parse(sub(str,start,end), FormatConstants.DATE_FORMATTER_HM);
    }

    /**
     * 往前补全 0（比如：股票代码为6位（数据原始要求长度），而现在数据为11111，则调用该方法会变成011111）
     *
     * @param data {@link Object} 现在的数据
     * @param length {@link int} 数据原始要求长度
     * @author lifuqiang
     * @date 2021/11/17 2:53 下午
     **/
    public static String completeForwardZero(Object data, int length) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(length);
        formatter.setGroupingUsed(false);
        return formatter.format(data).split("\\.")[0];
    }

    /**
     * BigDecimal前后补全0
     *
     * @param data {@link int} 数据
     * @param integerLength {@link int} 整数位数
     * @param decimalLength {@link int} 小数位数
     * @author lifuqiang
     * @date 2021/11/17 2:53 下午
     **/
    public static String toBigDecimalZeroPadding(BigDecimal data, int integerLength,int decimalLength) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(integerLength);
        formatter.setMinimumFractionDigits(decimalLength);
        formatter.setGroupingUsed(false);
        return formatter.format(data).replace(".","");
    }

    /**
     *获取空格位数
     */
    public static String getSpace(int num){
        return StrUtil.SPACE.repeat(Math.max(0, num));
    }
}
