package com.hitotek.workflow.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @Description 处理日期格式的工具类
 * @date Create at 2018/7/30
 */
public class DateUtils {
    /**
     * 提供默认日期格式化字符串的静态类
     */
    public static class DefaultType {
        public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
        public static final String WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";
        public static final String WITHOUT_TIME = "yyyy-MM-dd";
        public static final String CHINESE_SYMBOL_PREFIX = "yyyy年MM月dd日 HH:mm:ss";
        public static final String CHINESE_SYMBOL_SUFFIX = "yyyy-MM-dd HH时mm分ss秒";
        public static final String CHINESE_SYMBOL = "yyyy年MM月dd日 HH时mm分ss秒";
        public static final String ONLY_TODAY = "HH:mm:ss";
    }

    /**
     * UNIX时间戳 -> 日期对象
     *
     * @param timestamp 时间戳
     * @return 日期类
     */
    public static Date timestamp2Date(long timestamp) {
        return new Date(timestamp * 1000);
    }

    /**
     * 格式化日期
     *
     * @param target 目标日期
     * @param format 目标格式
     * @return 格式化日期字符串
     */
    public static String format(Date target, String format) {
        if (null != target && !Strings.isNullOrEmpty(format)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(target);
        }
        return "";
    }

    public static String getTodayZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return format(calendar.getTime(), DefaultType.FORMAT_DEFAULT);
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * 格式化日期
     *
     * @param timestamp UNIX时间戳
     * @param format    目标格式
     * @return 格式化日期字符串
     */
    public static String format(long timestamp, String format) {
        Date target = timestamp2Date(timestamp);
        if (!Strings.isNullOrEmpty(format)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(target);
        }
        return "";
    }

    public static Date from(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DefaultType.FORMAT_DEFAULT);
        try {
            return simpleDateFormat.parse(format);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 获得视频相对时间（最好是当天）
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 视频的相对时间
     */
    public static String getRelativeTimeFromTimestamp(Long start, Long end) {
        if (start > end) {
            throw new IllegalArgumentException("start time should not bigger than end time");
        }
        long offset = end - start;
        double result = (double) (offset / 1000);
        // 保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("0");
        return decimalFormat.format(result);
    }

    /**
     * 创建默认的格式化日期
     *
     * @param timestamp UNIX时间戳
     * @return 格式化日期字符串
     */
    public static String createDefault(long timestamp) {
        return format(timestamp, DefaultType.FORMAT_DEFAULT);
    }

    /**
     * 一次转化多个UNIX时间戳
     *
     * @param timestamp 可变参数，UNIX时间戳
     * @return 目标日期集合
     */
    public static List<Date> format(long... timestamp) {
        List<Date> result = new ArrayList<>();
        for (long current : timestamp) {
            result.add(timestamp2Date(current));
        }
        return result;
    }

    public static Date translate(String dateString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
