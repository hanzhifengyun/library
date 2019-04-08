package com.hanzhifengyun.library.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {


	public static final String PATTERN_DEFAULT = "yyyy年MM月dd日";
	public static final String PATTERN_DEFAULT_TIME = "yyyy年MM月dd日 HH:mm";
	public static final String PATTERN_SPRIT = "yyyy/MM/dd";
	public static final String PATTERN_TRANSVERSE = "yyyy-MM-dd";
	public static final String PATTERN_TRANSVERSE_TIME = "yyyy-MM-dd HH:mm";



	private static SimpleDateFormat getDateFormat(String pattern) {
		return new SimpleDateFormat(pattern, Locale.getDefault());
	}


	public static String format(Date date, String pattern) {
		return getDateFormat(pattern).format(date);
	}

	public static String format(long date, String pattern) {
		return format(new Date(date), pattern);
	}

	public static Date getCurrentDate() {
		return new Date();
	}

	public static long getCurrentDateTime() {
		return getCurrentDate().getTime();
	}

}