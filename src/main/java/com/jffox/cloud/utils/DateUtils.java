package com.jffox.cloud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具类
 *
 * @author chenjun
 * @version 1.0
 * @date 2016年3月23日下午1:49:31
 */
public class DateUtils {
    private static String ymdhms = "yyyy-MM-dd HH:mm:ss";

    private static String ymdh = "yyyy-MM-dd HH";

    private static String ym = "yyyyMM";

    private static String ymdhs = "yyyy-MM-dd HH:mm";

    private static String ymdhms2 = "yyyyMMddHHmmss";

    private static String ymd = "yyyy-MM-dd";

    private static String ymd1 = "yyyyMMdd";

    public static SimpleDateFormat ymdSDF = new SimpleDateFormat(ymd1);
    public static SimpleDateFormat ymdWithHyphenSDF = new SimpleDateFormat(ymd);

    private static String year = "yyyy";

    private static String month = "MM";

    private static String day = "dd";

    private static String hour = "HH";

    private static String minute = "mm";

    private static String second = "ss";

    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(ymdhms);

    public static SimpleDateFormat yyyyMM = new SimpleDateFormat(ym);

    public static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat(ymdhs);

    public static SimpleDateFormat yyyyMMddHH = new SimpleDateFormat(ymdh);

    public static SimpleDateFormat yyyyMMddHHmmss2 = new SimpleDateFormat(
            ymdhms2);

    public static SimpleDateFormat yearSDF = new SimpleDateFormat(year);

    public static SimpleDateFormat monthSDF = new SimpleDateFormat(month);

    public static SimpleDateFormat daySDF = new SimpleDateFormat(day);

    public static SimpleDateFormat hourSDF = new SimpleDateFormat(hour);

    public static SimpleDateFormat minuteSDF = new SimpleDateFormat(minute);

    public static SimpleDateFormat secondSDF = new SimpleDateFormat(second);

    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat yyyyMMddHH_NOT_ = new SimpleDateFormat(
            "yyyyMMdd");

    public static long DATEMM = 86400L;

    /**
     * 获得当前时间 格式：20151214171555
     *
     * @return String
     */
    public static String getCurrentTime2() {
        return yyyyMMddHHmmss2.format(new Date());
    }

    /**
     * 获得当前时间 格式：2014-12-02 10:38:53
     *
     * @return String
     */
    public static String getCurrentTime() {
        return yyyyMMddHHmmss.format(new Date());
    }

    /**
     * 可以获取昨天的日期 格式：2014-12-01
     *
     * @return String
     */
    public static String getYesterdayYYYYMMDD() {
        Date date = new Date(System.currentTimeMillis() - DATEMM * 1000L);
        String str = yyyyMMdd.format(date);
        try {
            date = yyyyMMddHHmmss.parse(str + " 00:00:00");
            return yyyyMMdd.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 可以获取后退N天的日期 格式：传入2 得到2014-11-30
     *
     * @param backDay
     * @return String
     */
    public static String getPreviousDay(int backDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Integer.parseInt("-" + backDay));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String back = sdf.format(calendar.getTime());
        return back;
    }

    /**
     * 可以获取后退N月的日期 格式：传入2 得到 前两月
     *
     * @param backMonth
     * @return
     */
    public static String getPreviousMonth(int backMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, backMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String back = sdf.format(calendar.getTime());
        return back;
    }

    /**
     * 可以获取后退N天的日期 格式：传入2 得到 前两天 日期格式：2014-11-30 00:00:00
     */
    public static String getStrDateByN1(String backDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Integer.parseInt("-" + backDay));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String back = sdf.format(calendar.getTime());
        return back;
    }

    /**
     * 可以获取后退N小时的日期
     *
     * @param backHour
     * @return String
     */
    public static String getLastNHour(int backHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, Integer.parseInt("-" + backHour));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH");
        String back = sdf.format(calendar.getTime());
        return back;
    }

    /**
     * 获取当前的年、月、日
     *
     * @return String
     */
    public static String getCurrentYear() {
        return yearSDF.format(new Date());
    }

    public static String getCurrentMonth() {
        return monthSDF.format(new Date());
    }

    public static String getCurrentDay() {
        return daySDF.format(new Date());
    }

    /**
     * 获取当前的时、分、秒
     *
     * @return
     */
    public static String getCurrentHour() {
        return hourSDF.format(new Date());
    }

    public static String getCurrentMinute() {
        return minuteSDF.format(new Date());
    }

    public static String getCurrentSecond() {
        return secondSDF.format(new Date());
    }

    /**
     * 获取年月日 也就是当前时间 格式：yyyyMMdd
     *
     * @return String
     */
    public static String getCurrentymd() {

        return ymdSDF.format(new Date());
    }

    /**
     * 获取年月日 也就是当前时间 格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentYmdWithHyph() {
        return ymdWithHyphenSDF.format(new Date());
    }

    /**
     * 获取今天0点开始的秒数
     *
     * @return long
     */
    public static long getTimeNumberToday() {
        Date date = new Date();
        String str = yyyyMMdd.format(date);
        try {
            date = yyyyMMdd.parse(str);
            return date.getTime() / 1000L;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取今天的日期 格式：20141202
     *
     * @return String
     */
    public static String getTodayString() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String str = format.format(date);
        return str;
    }

    /**
     * 获取昨天的日期 格式：20141201
     *
     * @return String
     */
    public static String getYesterdayString() {
        Date date = new Date(System.currentTimeMillis() - DATEMM * 1000L);
        String str = yyyyMMddHH_NOT_.format(date);
        return str;
    }

    /**
     * 获得昨天零点
     *
     * @return Date
     */
    public static Date getYesterDayZeroHour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal.getTime();
    }

    /**
     * 把long型日期转String ；---OK
     *
     * @param date   long型日期；
     * @param format 日期格式；
     * @return
     */
    public static String longToString(long date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt2 = new Date(date * 1000L);
        String sDateTime = sdf.format(dt2); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    /**
     * 获得今天零点
     *
     * @return Date
     */
    public static Date getTodayZeroHour() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal.getTime();
    }

    /**
     * 获得昨天23时59分59秒
     *
     * @return
     */
    public static Date getYesterDay24Hour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.HOUR, 23);
        return cal.getTime();
    }

    /**
     * String To Date ---OK
     *
     * @param date   待转换的字符串型日期；
     * @param format 转化的日期格式
     * @return 返回该字符串的日期型数据；
     */
    public static Date stringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获得指定日期所在的自然周的第一天，即周日
     *
     * @param date 日期
     * @return 自然周的第一天
     */
    public static Date getStartDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 1);
        date = c.getTime();
        return date;
    }

    /**
     * 获得指定日期所在的自然周的最后一天，即周六
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 7);
        date = c.getTime();
        return date;
    }

    /**
     * 获得指定日期所在当月第一天
     *
     * @return
     */
    public static Date getStartDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        date = c.getTime();
        return date;
    }

    /**
     * 获得指定日期所在当月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        date = c.getTime();
        return date;
    }

    /**
     * 获得指定日期的下一个月的第一天
     *
     * @param date
     * @return
     */
    public static Date getStartDayOfNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        date = c.getTime();
        return date;
    }

    /**
     * 获得指定日期的下一个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 2);
        c.add(Calendar.DATE, -1);
        date = c.getTime();
        return date;
    }

    /**
     * 求某一个时间向前多少秒的时间(currentTimeToBefer)---OK
     *
     * @param givedTime        给定的时间
     * @param interval         间隔时间的毫秒数；计算方式 ：n(天)*24(小时)*60(分钟)*60(秒)(类型)
     * @param format_Date_Sign 输出日期的格式；如yyyy-MM-dd、yyyyMMdd等；
     */
    public static String givedTimeToBefer(String givedTime, long interval,
                                          String format_Date_Sign) {
        String tomorrow = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format_Date_Sign);
            Date gDate = sdf.parse(givedTime);
            long current = gDate.getTime(); // 将Calendar表示的时间转换成毫秒
            long beforeOrAfter = current - interval * 1000L; // 将Calendar表示的时间转换成毫秒
            Date date = new Date(beforeOrAfter); // 用timeTwo作参数构造date2
            tomorrow = new SimpleDateFormat(format_Date_Sign).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tomorrow;
    }

    /**
     * 把String 日期转换成long型日期；---OK
     *
     * @param date   String 型日期；
     * @param format 日期格式；
     * @return
     */
    public static long stringToLong(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(date);
            // 继续转换得到秒数的long型
            lTime = dt2.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return lTime;
    }

    /**
     * 得到二个日期间的间隔日期；
     *
     * @param endTime   结束时间
     * @param beginTime 开始时间
     * @param isEndTime 是否包含结束日期；
     * @return
     */
    public static Map<String, String> getTwoDay(String endTime,
                                                String beginTime, boolean isEndTime) {
        Map<String, String> result = new HashMap<String, String>();
        if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime
                .equals(""))))
            return null;
        try {
            Date date = ymdSDF.parse(endTime);
            endTime = ymdSDF.format(date);
            Date mydate = ymdSDF.parse(beginTime);
            long day = (date.getTime() - mydate.getTime())
                    / (24 * 60 * 60 * 1000);
            result = getDate(endTime, Integer.parseInt(day + ""), isEndTime);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 得到二个日期间的间隔日期（单位是天）；
     *
     * @param endTime   结束时间
     * @param beginTime 开始时间
     * @param isEndTime 是否包含结束日期；
     * @return
     */
    public static Integer getTwoDayInterval(String endTime, String beginTime,
                                            boolean isEndTime) {
        if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime
                .equals(""))))
            return 0;
        long day = 0l;
        try {
            Date date = ymdSDF.parse(endTime);
            endTime = ymdSDF.format(date);
            Date mydate = ymdSDF.parse(beginTime);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return 0;
        }
        return Integer.parseInt(day + "");
    }

    public static Integer getTwoDayInterval(String endTime, String beginTime) {
        return getTwoDayInterval(endTime, beginTime, true);
    }

    /**
     * 得到二个日期间的间隔日期的秒数(单位是秒)；
     *
     * @param endTime   结束时间
     * @param beginTime 开始时间
     * @param isEndTime 是否包含结束日期；
     * @return
     */
    public static Integer getTwoDayIntervalS(String endTime, String beginTime,
                                             boolean isEndTime) {
        if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime
                .equals(""))))
            return 0;
        long s = 0l;
        try {
            Date date = yyyyMMddHHmmss.parse(endTime);
            endTime = yyyyMMddHHmmss.format(date);
            Date mydate = yyyyMMddHHmmss.parse(beginTime);
            s = (date.getTime() - mydate.getTime()) / 1000;
        } catch (Exception e) {
            return 0;
        }
        return Integer.parseInt(s + "");
    }

    /**
     * 根据身份证号输出年龄
     *
     * @param IdNO
     * @return
     */
    public static int IdNOToAge(String IdNO) {
        int leh = IdNO.length();
        String dates = "";
        if (leh == 18) {
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int u = Integer.parseInt(year) - Integer.parseInt(dates);
            return u;
        } else {
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }
    }

    public static String datetostring(String dt) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.ENGLISH);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "";
        try {
            time = sdf2.format(sdf1.parse(dt));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 根据结束时间以及间隔差值，求符合要求的日期集合；
     *
     * @param endTime
     * @param interval
     * @param isEndTime
     * @return
     */
    public static Map<String, String> getDate(String endTime, Integer interval,
                                              boolean isEndTime) {
        Map<String, String> result = new HashMap<String, String>();
        if (interval == 0 || isEndTime) {
            if (isEndTime)
                result.put(endTime, endTime);
        }
        if (interval > 0) {
            int begin = 0;
            for (int i = begin; i < interval; i++) {
                endTime = givedTimeToBefer(endTime, DATEMM, ymd);
                result.put(endTime, endTime);
            }
        }
        return result;
    }

    public static String getyyyyMM(String time) {
        try {
            return yyyyMM.format(yyyyMMddHHmmss.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatymd(Date date) {
        try {
            return yyyyMMdd.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 将time转为时间戳格式
     *
     * @param time 时间，格式：yyyy-MM-dd HH:mm:ss或yyyy-MM-dd或时间戳
     * @return
     */
    public static Long turnTime2Long(String time) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = null;
        Long timeLong = null;
        if (time.length() == 19 && time.contains(":")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(time);
                calendar.setTime(date);
                timeLong = calendar.getTimeInMillis();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (time.length() == 7 && time.contains("-") && !time.contains(":")) {
            sdf = new SimpleDateFormat("yyyy-MM");
            try {
                Date date = sdf.parse(time);
                calendar.setTime(date);
                calendar.set(Calendar.DATE, 01);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                timeLong = calendar.getTimeInMillis();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (RegUtil.isMatch(time, "\\d{13,}")) {
            //判断是不是时间戳，例如："1478329992827"
            try {
                timeLong = new Long(time);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            try {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                time = time.replace("年", "-").replace("月", "-");
                Date date = sdf.parse(time);
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                timeLong = calendar.getTimeInMillis();
            } catch (ParseException e) {
//				log.warn("time pattern is wrong ===>" +time);
            }
        }
        return timeLong;
    }

    /**
     * 判断是否是当天
     * @param time 格式：yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return
     */
    public static boolean isCurrentDay(String time) {
        if (time == null) return false;
        if (time.length() > 10) {
            time = time.substring(0,10);
        }
        if (getCurrentYmdWithHyph().equals(time)) {
            return true;
        }
        return false;
    }

    /**
     * 获取从开始时间到现在的秒数
     * @param startTime
     * @return
     */
    public  static Double getTimeInterval(Long startTime){
        return MathUtil.div((System.currentTimeMillis() - startTime),1000d);
    }

    public static void main(String[] args) {
        System.out.println(isCurrentDay("2019-08-27"));

    }
}
