package com.tda.timekeeping.util;

import com.tda.timekeeping.entity.AccountDetail;
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
    private static final long TIME_LUNCH = 5400000;
    private static final int MINUTES_TO_HOUR = 60;
    private static final int TIME_WITHOUT_SEC_PATTERN_LEN = "hh:mm".length();
    private static int CURRENT_MONTH = getTypeOfDate(new Date(), Calendar.MONTH) + 1;
    private static int CURRENT_YEAR = getTypeOfDate(new Date(), Calendar.YEAR);
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "hh:mm:ss";

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
     * @return int by format of time of of employee's working.
     */
    private static long setTimeWorkInDay(Date startTime, Date endTime) {
        long diff = 0;
        if (startTime != null && endTime != null) {
            int startHour = startTime.getHours();
            int endHour = endTime.getHours();
            int endMinutes = endTime.getMinutes();
            int startMinutes = startTime.getMinutes();
            if (startHour < 12) {
                if (endHour <= 12) {
                    diff = endTime.getTime() - startTime.getTime();
                } else if (endHour < 12 && endHour >= 13 && endMinutes >= 0 && endMinutes <= 30) {
                    endTime.setHours(12);
                    endTime.setMinutes(00);
                    diff = endTime.getTime() - startTime.getTime();
                } else {
                    diff = endTime.getTime() - startTime.getTime() - TIME_LUNCH;
                }
            } else if (startHour >= 12 && startHour <= 13 && startMinutes >= 0 && startMinutes <= 30) {
                startTime.setHours(13);
                startTime.setMinutes(30);
                diff = endTime.getTime() - startTime.getTime();
            } else {
                diff = endTime.getTime() - startTime.getTime();
            }
        }
        return diff;
    }

    /**
     * Total not work in office of employee in current month.
     *
     * @param lists: List time work of employee in current month.
     * @return number is total not work in office of employee.
     */
    public static int totalNotWorkInOffice(List<AccountDetailVo> lists) {
        int total = 0;
        for (AccountDetailVo accountDetailVo : lists) {
            if (!accountDetailVo.hourIsEmpty()) {
                long time = setTimeWorkInDay(accountDetailVo.getStartTime(), accountDetailVo.getEndTime());
                if (time == 0) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Total  work in office of employee in current month.
     *
     * @param lists: List time work of employee in current month.
     * @return number is total  work in office of employee.
     */
    public static String totalWorkInMonth(List<AccountDetailVo> lists) {
        long total = 0;
        for (AccountDetailVo accountDetailVo : lists) {
            total += setTimeWorkInDay(accountDetailVo.getStartTime(), accountDetailVo.getEndTime());
        }
        return formatTotalTime(total);
    }

    /**
     * List Day Work but full 8 hour in current month.
     *
     * @param lists: List time work of employee in current month.
     * @return String is list day work not full of employee.
     */
    public static String listDayWorkNotFull(List<AccountDetailVo> lists) {
        String listDayNotFull = "";
        if (lists.isEmpty()) {
            return listDayNotFull;
        } else {
            for (AccountDetailVo accountDetailVo : lists) {
                if (!accountDetailVo.hourIsEmpty()) {
                    long hour = Long.valueOf(accountDetailVo.getHour().split(":")[0]);
                    if (hour < 8) {
                        listDayNotFull += accountDetailVo.getWorkDate() + ", ";
                    }
                }
            }
        }
        return listDayNotFull.length() > 0 ? listDayNotFull.substring(0, listDayNotFull.length() - 2) : "";
    }

    public static AccountDetail getAccountDetailWithNewInfo(String startTimeStr, String endTimeStr, String sendMail, String note) {
        AccountDetail accountDetail = null;
        if (startTimeStr.length() > 0 && endTimeStr.length() > 0) {
            try {
                Time startTime = convert(startTimeStr);
                Time endTime = convert(endTimeStr);
                accountDetail = new AccountDetail();
                accountDetail.setStartTime(startTime);
                accountDetail.setEndTime(endTime);
                accountDetail.setNote(note);
                accountDetail.setCheckEmail(Integer.valueOf(sendMail));
                return accountDetail;
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return accountDetail;
    }

    /**
     * Covert String to Time.
     *
     * @param s: String to covert
     * @return Time with format: hh:mm:ss
     * @throws ParseException: When string not right format
     */
    private static Time convert(String s) {
        int len = s.length();
        if (len == TIME_WITHOUT_SEC_PATTERN_LEN) {
            s = s + ":00";
        }
        if (len > TIME_FORMAT.length()) {
            s = s.substring(0, TIME_FORMAT.length());
        }
        return java.sql.Time.valueOf(s);
    }

    public static int checkMonthChoose(String monthChoose) {
        return monthChoose == null ? CURRENT_MONTH : Integer.valueOf(monthChoose);
    }

    public static int checkYearChoose(String yearChoose) {
        return yearChoose == null ? CURRENT_YEAR : Integer.valueOf(yearChoose);
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

    public static List<String> getAllYear() {
        List<String> lists = new ArrayList<>();
        for (int i = CURRENT_YEAR - 5; i < CURRENT_YEAR + 5; i++) {
            lists.add(String.valueOf(i));
        }
        return lists;
    }

    public static String dispatcher(String role, String pageFirst, String pageSecond) {
        return role.equalsIgnoreCase("USER") ? pageFirst : pageSecond;
    }

    public static String formatTime(Date startTime, Date endTime) {
        long time = setTimeWorkInDay(startTime, endTime);
        long hour = TimeUnit.MILLISECONDS.toHours(time);
        long minutes = (TimeUnit.MILLISECONDS.toMinutes(time) % MINUTES_TO_HOUR);
        return time > 0 ? String.format("%02d:%02d", hour, minutes) : "";
    }

    private static String formatTotalTime(long time) {
        NumberFormat formatNumber = new DecimalFormat("#0.00");
        long hour = TimeUnit.MILLISECONDS.toHours(time);
        long minutes = (TimeUnit.MILLISECONDS.toMinutes(time) % MINUTES_TO_HOUR);
        return formatNumber.format(hour + (double) minutes / MINUTES_TO_HOUR);
    }
}
