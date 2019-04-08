package com.hanzhifengyun.library.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 金额相关工具
 */

public class AmountUtil {

    /**
     * @return 两数相乘保留两位小数
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.multiply(b).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * @return 两数相除保留两位小数
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null || b.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return a.divide(b, 2, RoundingMode.HALF_UP);
    }


    /**
     * 数值格式化
     * 保留两位小数
     */
    public static String getFormatAmount(String amount) {
        BigDecimal bigDecimal;
        if (amount == null) {
            bigDecimal = BigDecimal.ZERO;
        } else {
            bigDecimal = new BigDecimal(amount);
        }
        return getFormatAmount(bigDecimal);
    }

    /**
     * 数值格式化
     * 保留两位小数
     */
    public static String getFormatAmount(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "0.00";
        }
        String result;
        //保留两位小数
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            result = df.format(bigDecimal);
        } catch (Exception e) {
            e.printStackTrace();
            return bigDecimal.toString();
        }
        return result;
    }

    /**
     * 数值格式化
     * 保留两位小数
     */
    public static String getFormatAmount(double amount) {
        return getFormatAmount(new BigDecimal(amount));
    }

    public static String getAvailableAmountText(String sourceText, String appendText) {
        if (TextUtil.isEmpty(sourceText)) {
            if (KeyboardConstant.DOT.equals(appendText)) {
                return "0.";
            } else {
                return appendText;
            }
        }
        if (KeyboardConstant.DOT.equals(appendText)) {
            if (sourceText.contains(KeyboardConstant.DOT)) {
                return sourceText;
            }
        } else {
            if (sourceText.contains(KeyboardConstant.DOT)) {
                int indexOf = sourceText.indexOf(KeyboardConstant.DOT);
                if ((sourceText.length() - indexOf) > 2) {
                    return sourceText;
                }
            } else {
                if (KeyboardConstant.ZERO.equals(sourceText)) {
                    return appendText;
                }
            }
        }
        return sourceText + appendText;
    }

    /**
     * 提供精确加法运算
     */
    public static BigDecimal addition(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.add(b).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 提供精确减法运算
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.subtract(b).setScale(2, RoundingMode.HALF_UP);
    }


}
