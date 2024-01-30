package com.justvastness.webchat.config.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * BigDecimal 工具类
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/11/10 7:36 下午
 **/
public class BigDecimalUtil {

    private static final Integer DEFAULT_SCALE = 10;
    private static final RoundingMode DEFAULT_MODE = RoundingMode.HALF_EVEN;

    /********************************** 加 *****************************************/

    /**
     * 加
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1 {@link Object} var1
     * @param var2 {@link Object} var2
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal add(Object var1, Object var2) {
        return add(var1, var2, null, null);
    }

    /**
     * 加
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1  {@link Object} var1
     * @param var2  {@link Object} var2
     * @param scale {@link Integer} 精度
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal add(Object var1, Object var2, Integer scale) {
        return add(var1, var2, scale, null);
    }

    /**
     * 加
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param roundingMode {@link } 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal add(Object var1, Object var2, RoundingMode roundingMode) {
        return add(var1, var2, null, roundingMode);
    }

    /**
     * 加
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param scale        {@link Integer} 精度
     * @param roundingMode {@link } 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal add(Object var1, Object var2, Integer scale, RoundingMode roundingMode) {
        if (ObjectUtil.isNull(var1)) {
            return toBigDecimal(var2);
        }
        if (ObjectUtil.isNull(var2)) {
            return toBigDecimal(var1);
        }
        BigDecimal var3 = toBigDecimal(var1);
        BigDecimal var4 = toBigDecimal(var2);
        if (isZero(var3)) {
            return var4;
        }
        if (isZero(var4)) {
            return var3;
        }
        if (ObjectUtil.isNull(scale)) {
            scale = DEFAULT_SCALE;
        }
        if (ObjectUtil.isNull(roundingMode)) {
            roundingMode = DEFAULT_MODE;
        }
        return var3.add(var4).setScale(scale, roundingMode);
    }

    /********************************** 减 *****************************************/

    /**
     * 减
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1  {@link Object} var1
     * @param var2  {@link Object} var2
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal subtract(Object var1, Object var2) {
        return subtract(var1, var2, null, null);
    }

    /**
     * 减
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1  {@link Object} var1
     * @param var2  {@link Object} var2
     * @param scale {@link Integer} 精度
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal subtract(Object var1, Object var2, Integer scale) {
        return subtract(var1, var2, scale, null);
    }

    /**
     * 减
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param roundingMode {@link } 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal subtract(Object var1, Object var2, RoundingMode roundingMode) {
        return subtract(var1, var2, null, roundingMode);
    }

    /**
     * 减
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param scale        {@link Integer} 精度
     * @param roundingMode {@link } 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal subtract(Object var1, Object var2, Integer scale, RoundingMode roundingMode) {
        if (ObjectUtil.isNull(var1) || ObjectUtil.isNull(var2)) {
            return BigDecimal.ZERO;
        }
        BigDecimal var3 = toBigDecimal(var1);
        BigDecimal var4 = toBigDecimal(var2);
        if (ObjectUtil.isNull(scale)) {
            scale = DEFAULT_SCALE;
        }
        if (ObjectUtil.isNull(roundingMode)) {
            roundingMode = DEFAULT_MODE;
        }
        if (isZero(var3)) {
            // 取反
            return var4.negate().setScale(scale, roundingMode);
        }
        if (isZero(var4)) {
            return var3;
        }
        return var3.subtract(var4).setScale(scale, roundingMode);
    }


    /********************************** 乘 *****************************************/

    /**
     * 乘
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1 {@link Object} var1
     * @param var2 {@link Object} var2
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal multiply(Object var1, Object var2) {
        return multiply(var1, var2, null, null);
    }

    /**
     * 乘
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1  {@link Object} var1
     * @param var2  {@link Object} var2
     * @param scale {@link Integer} 精度
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal multiply(Object var1, Object var2, Integer scale) {
        return multiply(var1, var2, scale, null);
    }

    /**
     * 乘
     * 支持类似于 1,234.55 格式的数字
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param roundingMode {@link } 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal multiply(Object var1, Object var2, RoundingMode roundingMode) {
        return multiply(var1, var2, null, roundingMode);
    }

    /**
     * 乘
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param scale        {@link Integer} 精度
     * @param roundingMode {@link } 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal multiply(Object var1, Object var2, Integer scale, RoundingMode roundingMode) {
        // 只要其中一个 为 NULL 或者为空 直接返回 0
        if (ObjectUtil.isNull(var1) || ObjectUtil.isNull(var2)) {
            return BigDecimal.ZERO;
        }
        BigDecimal var3 = toBigDecimal(var1);
        BigDecimal var4 = toBigDecimal(var2);
        // 其中一个为 0 直接返回 0
        if (isZero(var3) || isZero(var4)) {
            return BigDecimal.ZERO;
        }
        if (ObjectUtil.isNull(scale)) {
            scale = DEFAULT_SCALE;
        }
        if (ObjectUtil.isNull(roundingMode)) {
            roundingMode = DEFAULT_MODE;
        }
        return var3.multiply(var4).setScale(scale, roundingMode);
    }

    /********************************** 除 *****************************************/

    /**
     * 除
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1 {@link Object} var1
     * @param var2 {@link Object} var2
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal divide(Object var1, Object var2) {
        return divide(var1, var2, null, null);
    }

    /**
     * 除
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1  {@link Object} var1
     * @param var2  {@link Object} var2
     * @param scale {@link Integer} 精度
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal divide(Object var1, Object var2, Integer scale) {
        return divide(var1, var2, scale, null);
    }

    /**
     * 除
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param roundingMode {@link RoundingMode} 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal divide(Object var1, Object var2, RoundingMode roundingMode) {
        return divide(var1, var2, null, roundingMode);
    }

    /**
     * 除
     * <p>
     * 支持类似于 1,234.55 格式的数字
     * </p>
     *
     * @param var1         {@link Object} var1
     * @param var2         {@link Object} var2
     * @param scale        {@link Integer} 精度
     * @param roundingMode {@link } 舍入法
     * @return java.math.BigDecimal
     * @author wangdong
     * @date 2021/11/10 7:53 下午
     **/
    public static BigDecimal divide(Object var1, Object var2, Integer scale, RoundingMode roundingMode) {
        // 其中一个为 NULL 直接返回 0
        if (ObjectUtil.isNull(var1) || ObjectUtil.isNull(var2)) {
            return BigDecimal.ZERO;
        }
        BigDecimal var3 = toBigDecimal(var1);
        BigDecimal var4 = toBigDecimal(var2);
        // 其中一个为 0 直接返回 0
        if (isZero(var3) || isZero(var4)) {
            return BigDecimal.ZERO;
        }
        if (ObjectUtil.isNull(scale)) {
            scale = DEFAULT_SCALE;
        }
        if (ObjectUtil.isNull(roundingMode)) {
            roundingMode = DEFAULT_MODE;
        }
        return var3.divide(var4, scale, roundingMode);
    }

    public static boolean isZero(BigDecimal var) {
        return BigDecimal.ZERO.compareTo(var) == 0;
    }

    public static BigDecimal toBigDecimal(Object number) {
        if (Objects.isNull(number)) {
            return BigDecimal.ZERO;
        }
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        if (number instanceof Integer) {
            return new BigDecimal((Integer) number);
        }
        if (number instanceof Long) {
            return new BigDecimal((Long) number);
        }
        if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        }
        // Float、Double等有精度问题，转换为字符串后再转换
        return NumberUtil.toBigDecimal(number.toString());
    }
}
