package com.hanzhifengyun.library.util;


import java.math.BigDecimal;

/**
 * Number parse util
 */

public class NumberParseUtil {

    /**
     * string to int
     * @param countStr string number
     * @return int ， if throw NumberFormatException then return 0
     */
    public static int parseInt(String countStr) {
        return parseInt(countStr, 0);
    }

    /**
     * string to int
     * @param countStr string number
     * @return int ， if throw NumberFormatException then return defaultValue
     */
    public static int parseInt(String countStr, int defaultValue) {
        int count = defaultValue;
        if (!TextUtil.isEmpty(countStr)) {
            try {
                count = Integer.parseInt(countStr);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return count;
    }



    /**
     * string to double
     * @param countStr string number
     * @return double ， if throw NumberFormatException then return 0
     */
    public static double parseDouble(String countStr) {
        return parseDouble(countStr, 0);
    }

    /**
     * string to double
     * @param countStr string number
     * @return double ， if throw NumberFormatException then return defaultValue
     */
    public static double parseDouble(String countStr, double defaultValue) {
        double count = defaultValue;
        if (!TextUtil.isEmpty(countStr)) {
            try {
                count = Double.parseDouble(countStr);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return count;
    }


    /**
     * string to long
     * @param countStr string number
     * @return long ， if throw NumberFormatException then return 0
     */
    public static long parseLong(String countStr) {
        return parseLong(countStr, 0);
    }

    /**
     * string to long
     * @param countStr string number
     * @return long ， if throw NumberFormatException then return defaultValue
     */
    public static long parseLong(String countStr, long defaultValue) {
        long count = defaultValue;
        if (!TextUtil.isEmpty(countStr)) {
            try {
                count = Long.parseLong(countStr);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return count;
    }


    /**
     * string to BigDecimal
     * @param decimalStr string Decimal
     * @return BigDecimal ， if throw Exception then return BigDecimal.ZERO
     */
    public static BigDecimal parseBigDecimal(String decimalStr) {
        BigDecimal result = BigDecimal.ZERO;
        if (!TextUtil.isEmpty(decimalStr)) {
            try {
                return new BigDecimal(decimalStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
