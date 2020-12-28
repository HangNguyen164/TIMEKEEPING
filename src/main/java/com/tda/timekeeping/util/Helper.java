package com.tda.timekeeping.util;

import com.tda.timekeeping.vo.AccountDetailVo;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {
    private static final int HOUR_LUNCH = 1;
    private static final int MINUTES_LUNCH = 30;
    private static final int MINUTES_TO_HOUR = 60;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "hh:mm:ss";
    private static final int TIME_WITHOUT_SEC_PATTERN_LEN = "hh:mm".length();
    private static final int TIME_PATTERN_LEN = "hh:mm:ss".length();

    public static int getTypeOfDate(Date date, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(type);
    }

    /**
     * Calculate the employee's working time of the day.
     *
     * @param startTime: Time start work in day in live office.
     * @param endTime:   Time stop work in day in live office.
     * @return String by format of time of of employee's working.
     */
    public static String setTimeWorkInDay(Date startTime, Date endTime) {
        if (startTime != null && endTime != null) {
            long diff = endTime.getTime() - startTime.getTime();
            long hour = TimeUnit.MILLISECONDS.toHours(diff) - HOUR_LUNCH;
            long minutes = (TimeUnit.MILLISECONDS.toMinutes(diff) % MINUTES_TO_HOUR) - MINUTES_LUNCH;
            if (minutes < 0) {
                hour--;
                minutes = MINUTES_TO_HOUR - Math.abs(minutes);
            }
            return String.format("%02d:%02d", hour, minutes);
        }
        return "";
    }

    /**
     * Total not work in office of employee in current month.
     *
     * @param lists:        List time work of employee in current month.
     * @param currentMonth: current month in year.
     * @return number is total not work in office of employee.
     */
    public static int totalNotWorkInOffice(List<AccountDetailVo> lists, int currentMonth) {
        int total = 0;
        for (AccountDetailVo accountDetailVo : lists) {
            int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH);
            if (month == currentMonth && accountDetailVo.getSendEmail() != 0) {
                if (!accountDetailVo.hourIsEmpty()) {
                    long hour = Long.valueOf(accountDetailVo.getHour().split(":")[0]);
                    if (hour == 0) {
                        total++;
                    }
                }
            }
        }
        return total;
    }

    /**
     * Total  work in office of employee in current month.
     *
     * @param lists:        List time work of employee in current month.
     * @param currentMonth: current month in year.
     * @return number is total  work in office of employee.
     */
    public static String totalWorkInMonth(List<AccountDetailVo> lists, int currentMonth) {
        double hour = 0;
        double minutes = 0;
        NumberFormat formatNumber = new DecimalFormat("#0.00");
        for (AccountDetailVo accountDetailVo : lists) {
            int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH);
            if (month == currentMonth) {
                if (!accountDetailVo.hourIsEmpty()) {
                    hour += Long.valueOf(accountDetailVo.getHour().split(":")[0]);
                    minutes += Long.valueOf(accountDetailVo.getHour().split(":")[1]);
                }
            }
        }
        return formatNumber.format(hour + minutes / MINUTES_TO_HOUR);
    }

    /**
     * List Day Work but full 8 hour in current month.
     *
     * @param lists:        List time work of employee in current month.
     * @param currentMonth: current month in year.
     * @return String is list day work not full of employee.
     */
    public static String listDayWorkNotFull(List<AccountDetailVo> lists, int currentMonth) {
        String listDayNotFull = "";
        if (lists.isEmpty()) {
            return listDayNotFull;
        } else {
            for (AccountDetailVo accountDetailVo : lists) {
                int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH);
                if (month == currentMonth) {
                    if (!accountDetailVo.hourIsEmpty()) {
                        long hour = Long.valueOf(accountDetailVo.getHour().split(":")[0]);
                        if (hour < 8) {
                            listDayNotFull += accountDetailVo.getWorkDate() + ", ";
                        }
                    }
                }
            }
        }
        return listDayNotFull.length() > 0 ? listDayNotFull.substring(0, listDayNotFull.length() - 2) : "";
    }

    public static Time convert(String s) throws ParseException {
        int len = s.length();
        if (len == TIME_WITHOUT_SEC_PATTERN_LEN) {
            s = s + ":00";
        }
        if (len > TIME_PATTERN_LEN) {
            s = s.substring(0, TIME_PATTERN_LEN);
        }
        return java.sql.Time.valueOf(s);
    }

    public static List<String> getAllMonth() {
        List<String> lists = new ArrayList<>();
        lists.add("January");
        lists.add("February");
        lists.add("March");
        lists.add("April");
        lists.add("May");
        lists.add("June");
        lists.add("July");
        lists.add("August");
        lists.add("September");
        lists.add("October");
        lists.add("November");
        lists.add("December");
        return lists;
    }
}
