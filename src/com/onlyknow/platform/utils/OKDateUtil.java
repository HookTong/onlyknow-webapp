package com.onlyknow.platform.utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class OKDateUtil {

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    public static String DateToStr(Date date, int number) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format;
        switch (number) {
            case 6:
                format = new SimpleDateFormat("yyyyMM");
                return format.format(date);
            case 8:
                format = new SimpleDateFormat("yyyyMMdd");//设置日期格式
                return format.format(date);
            case 10:
                format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                return format.format(date);
            default:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                return format.format(date);
        }
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date LongToDate(long time) {
        return new Date(time);
    }


    public static Date getCurrentDate() throws ParseException {
        return new Date();
    }

    /**
     * 解析 yyyy-MM-dd 字符串为日期
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return timeformat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析 yyyy-MM-dd hh:mm:ss字符串为日期时间
     *
     * @param date
     * @return
     */
    public static Date parseTime(String date) {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return timeformat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 解析 Wed Sep 08 15:45:15 2010  字符串为日期时间
     *
     * @param date
     * @return
     */
    public static Date parseStdTime(String date) {
        SimpleDateFormat timeformat = new SimpleDateFormat();
        try {
            return timeformat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentDate(int number) {
        SimpleDateFormat df;
        switch (number) {
            case 6:
                df = new SimpleDateFormat("yyMMdd");//设置日期格式
                return df.format(new Date());
            case 8:
                df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
                return df.format(new Date());
            case 10:
                df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                return df.format(new Date());
            default:
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                return df.format(new Date());
        }

    }

    /**
     * 字符串转换成日期
     *
     * @param strDate   转换对象
     * @param strFormat 需要转换的格式，例：yyyy-MM-dd HH:mm:ss
     * @return date
     */
    public static Date StrToDate(String strDate, String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date computing(Date obj, int field, int amonut) {
        /*
         *java中对日期的加减操作
         *gc.add(1,-1)表示年份减一.
         *gc.add(2,-1)表示月份减一.
         *gc.add(3.-1)表示周减一.
         *gc.add(5,-1)表示天减一.
         *以此类推应该可以精确的毫秒吧.没有再试.大家可以试试.
         *GregorianCalendar类的add(int field,int amount)方法表示年月日加减.
         *field参数表示年,月.日等.
         *amount参数表示要加减的数量.
         */
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(obj);
        gc.add(field, amonut);
        return gc.getTime();
    }

    /*
     * 时间转日期 今天多少,昨天多少
     * @param date
     * @return
     */
    public static long daysBetween(Date startTime, Date endTime) {
        if ((startTime == null) || (endTime == null)) {
            return 0;
        }
        long ld1 = startTime.getTime();
        long ld2 = endTime.getTime();

        long days = (long) ((ld2 - ld1) / 86400000);
        return days;
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween2(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String dateConvertStr(Date date) {
        if (date == null) {
            return "";
        }
        String now = getCurrentDate(10);
        Date today = OKDateUtil.StrToDate(now + " 00:00:00");

        String unnow = DateToStr(date, 10);
        Date untoday = OKDateUtil.StrToDate(unnow + " 00:00:00");
        long days = daysBetween(untoday, today);

        if (days == 0) {
            long _day = (long) (new Date().getTime() - date.getTime()) / 1000;
            if (_day < 60) {
                return "1分钟前";
            }
            _day = (long) _day / 60;
            if (_day < 60) {
                return _day + " 分钟前";
            }
            _day = (long) _day / 60;
            if (_day < 24) {
                return _day + " 小时前";
            }
        }
        if (days == 1) {

            return "昨天: " + OKDateUtil.DateToStr(date).substring(11, 16);
        }
        if (days < 31) {
            return days + " 天前";
        }
        return DateToStr(date, 0);
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date _StrToDate(String str) {
        if (!OKStringUtil.isNotBlank(str)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算2个时间直接的小时差毫秒数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static long millisecondBetween(Date startTime, Date endTime) {
        if ((startTime == null) || (endTime == null)) {
            return 0;
        }
        long ld1 = startTime.getTime();
        long ld2 = endTime.getTime();
        return ld2 - ld1;
    }

    /**
     * 计算2个时间直接的小时数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static long hourBetween(Date startTime, Date endTime) {
        if ((startTime == null) || (endTime == null)) {
            return 0;
        }
        long ld1 = startTime.getTime();
        long ld2 = endTime.getTime();
        return (ld2 - ld1) / (3600 * 1000);
    }

    /**
     * 验证一个时间间隔是否在在2个时间间隔之间
     * 失效返回true,否则返回false
     *
     * @param startTime
     * @param endTime
     * @param validate
     * @return
     */
    public static boolean validateTimeIsEffect(Date startTime, Date endTime, int validate) {
        if ((startTime == null) || (endTime == null)) {
            return true;
        }
        long ld1 = startTime.getTime();
        long ld2 = endTime.getTime();

        long valiate_interval = ld2 - ld1;
        if (valiate_interval <= validate * 60 * 60 * 1000) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 时间相减得到天数
     *
     * @param beginDate
     * @param endDate
     * @return return long
     * @author foxmen
     */
    public static long getDaySub(Date beginDate, Date endDate) {
        long day = 0;
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 根据util时间得到Sql时间
     *
     * @param getSQLDateByUtilDate
     * @param java.util.Date
     * @return java.sql.Date
     * @author foxmen
     */
    public static java.sql.Date getSQLDateByUtilDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 根据Sql时间得到util时间
     *
     * @param getUtilDateBySQLDate
     * @param java.sql.Date
     * @return java.util.Date
     * @author foxmen
     */
    public static Date getUtilDateBySQLDate(java.sql.Date date) {
        return new Date(date.getTime());
        //return (java.util.Date)date;
    }

    /***
     * 根据当前时间取days天后的时间
     * @param days
     * @return
     */
    public static Date getAfterAFewDays(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /***
     * 取days天后的时间
     * @param days
     * @return
     */
    public static Date getAfterAFewDays(Date d, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /**
     * 获得上n月或下n月
     * 上一个月 1;下一个月 -1
     *
     * @param month
     * @return
     */
    public static Date getDateByMonth(int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - month);
        return c.getTime();
    }

    /**
     * add by cupai
     * 获取几天前时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.DAY_OF_MONTH, -day);
        return now.getTime();
    }

    /**
     * add by xupai
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBeforeMonth(Date d, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.MONTH, -month);
        return now.getTime();
    }

    /***
     * 获取两个时间段内的日期列表
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<Date> getDateList(Date startDate, Date endDate) throws Exception {
        List<Date> dateList = new ArrayList<Date>();
        long start = startDate.getTime();
        long end = endDate.getTime();
        if (start > end) {
            throw new Exception("开始时间不能大于结束时间！");
        }
        int days = OKDateUtil.getBetweenDays(startDate, endDate);

        dateList.add(startDate);
        for (int i = 1; i <= days; i++) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(startDate);
            cl.set(Calendar.DATE, cl.get(Calendar.DATE) + i);
            dateList.add(cl.getTime());
        }
        return dateList;
    }

    /***
     * 前几天或后几天的时间列表
     * @param sourceDate 参照时间
     * @param calculate "prev" or "next"
     * @param days
     * @return
     * @throws Exception
     */
    public List<Date> getDateList(Date sourceDate, String calculate, int days) throws Exception {
        List<Date> dateList = new ArrayList<Date>();
        if (OKStringUtil.equals(calculate, "prev")) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(sourceDate);
            cl.set(Calendar.DATE, cl.get(Calendar.DATE) - days);
            return getDateList(cl.getTime(), sourceDate);
        }
        if (OKStringUtil.equals(calculate, "next")) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(sourceDate);
            cl.set(Calendar.DATE, cl.get(Calendar.DATE) + days);
            return getDateList(sourceDate, cl.getTime());
        }
        return dateList;
    }

    /**
     * Date转XMLGregorianCalendar
     *
     * @param date
     * @return
     */
    public XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return gc;
    }

    /**
     * XMLGregorianCalendar转Date
     *
     * @param cal
     * @return
     * @throws Exception
     */
    public Date convertToDate(XMLGregorianCalendar cal) throws Exception {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    /**
     * 计算2个时间直接的小时差毫秒数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static long millisecondsBetween(Date startTime, Date endTime) {
        if ((startTime == null) || (endTime == null)) {
            return 0;
        }
        long ld1 = startTime.getTime();
        long ld2 = endTime.getTime();
        return ld2 - ld1;
    }

    /**
     * 计算2个时间直接的小时数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static long hoursBetween(Date startTime, Date endTime) {
        return millisecondsBetween(startTime, endTime) / (1 * 60 * 60 * 1000);
    }

    /**
     * 日期转换为字符串
     *
     * @param date    转换的对象
     * @param format  字符串格式
     * @param nullStr 当对象为空时返回的自定义字符串
     * @return
     */
    public static String formatDate(Date date, String format, String nullStr) {
        if (date == null) {
            return nullStr == null ? "" : nullStr;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatDateToDateTimeStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String formatDateToDateStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static boolean isActiveTime(int hour, int start, int end) {
        if (start == 0 && end == 0) {
            return true;
        }
        if (start <= end) {
            if (hour >= start && hour <= end)
                return true;
            else
                return false;
        }
        if (start > end) {
            if (hour >= end && hour < start)
                return false;
            else
                return true;
        }
        return false;
    }

    public static int getKeepSecond(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / 1000);
    }

    public static String getKeepTimeDescript(int sec) {
        int days = (int) (sec / 86400);
        int hours = (int) (sec % 86400 / 3600);
        int minutes = (int) (sec % 3600 / 60);
        int seconds = (int) (sec % 60);
        return days + " 天 " + hours + " 小时 " + minutes + " 分 " + seconds + " 秒 ";
    }

    /**
     * @param str
     * @param formatStr
     * @return
     */
    public static Date strToDate(String str, String formatStr) {
        SimpleDateFormat formattxt = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = formattxt.parse(str);
        } catch (Exception e) {
            date = new Date();
            e.printStackTrace();
        }
        return date;
    }

    public static final int ADDYEAR = 1;
    public static final int ADDMONTH = 2;
    public static final int ADDWEEK = 3;
    public static final int ADDDAY = 5;

    public static Date add(Date date, int addType, int val) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(addType, val);
        return calendar.getTime();
    }

    public static long getCurrentTime() {
        return (new Date()).getTime();
    }

    /**
     * 返回 输入时间的 某个整点时间
     *
     * @param date
     * @param hour 整点
     * @return Date
     * @Description
     * @author peng.wang
     * @date 2015-6-19 下午1:31:52
     */
    public static Date getHourOfDay(Date date, int hour) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.DATE, cl.get(Calendar.DATE));
        cl.set(Calendar.HOUR_OF_DAY, hour);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        cl.set(Calendar.MILLISECOND, 0);
        Date rsDate = cl.getTime();
        return rsDate;
    }

    /**
     * 返回 输入时间的 某时时间
     *
     * @param date   当前时间
     * @param hour   小时
     * @param minute 分钟
     * @param second 秒
     * @return Date
     * @Description
     * @author peng.wang
     * @date 2015-6-19 下午1:33:47
     */
    public static Date getTimeOfDay(Date date, int hour, int minute, int second) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.DATE, cl.get(Calendar.DATE));
        cl.set(Calendar.HOUR_OF_DAY, hour);
        cl.set(Calendar.MINUTE, minute);
        cl.set(Calendar.SECOND, second);
        cl.set(Calendar.MILLISECOND, 0);
        Date rsDate = cl.getTime();
        return rsDate;
    }

    public static Date parseDate(String str, String... parsePatterns) throws ParseException {
        return parseDateWithLeniency(str, parsePatterns, true);
    }

    private static Date parseDateWithLeniency(
            String str, String[] parsePatterns, boolean lenient) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }

        SimpleDateFormat parser = new SimpleDateFormat();
        parser.setLenient(lenient);
        ParsePosition pos = new ParsePosition(0);
        for (String parsePattern : parsePatterns) {

            String pattern = parsePattern;

            // LANG-530 - need to make sure 'ZZ' output doesn't get passed to SimpleDateFormat
            if (parsePattern.endsWith("ZZ")) {
                pattern = pattern.substring(0, pattern.length() - 1);
            }

            parser.applyPattern(pattern);
            pos.setIndex(0);

            String str2 = str;
            // LANG-530 - need to make sure 'ZZ' output doesn't hit SimpleDateFormat as it will ParseException
            if (parsePattern.endsWith("ZZ")) {
                str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
            }

            Date date = parser.parse(str2, pos);
            if (date != null && pos.getIndex() == str2.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }

    public static int getBetweenDays(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.after(c2)) {
            c1.setTime(d2);
            c2.setTime(d1);
        }

        int betweenYears = c2.get(1) - c1.get(1);
        int betweenDays = c2.get(6) - c1.get(6);

        for (int i = 0; i < betweenYears; ++i) {
            betweenDays += c1.getActualMaximum(6);
            c1.set(1, c1.get(1) + 1);
        }

        return betweenDays;
    }
}
