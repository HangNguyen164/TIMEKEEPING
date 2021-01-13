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
    private static final int HOUR_LUNCH = 1;
    private static final int MINUTES_LUNCH = 30;
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
            return hour > 0 ? String.format("%02d:%02d", hour, minutes) : "00:00";
        }
        return "";
    }

    /**
     * Total not work in office of employee in current month.
     *
     * @param lists:        List time work of employee in current month.
     * @param currentMonth: current month in year.
     * @param currentYear:  current year
     * @return number is total not work in office of employee.
     */
    public static int totalNotWorkInOffice(List<AccountDetailVo> lists, int currentMonth, int currentYear) {
        int total = 0;
        for (AccountDetailVo accountDetailVo : lists) {
            int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH) + 1;
            int year = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.YEAR);
            if (month == currentMonth && year == currentYear && accountDetailVo.getSendEmail() != 0) {
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
     * @param currentYear:  current year.
     * @return number is total  work in office of employee.
     */
    public static String totalWorkInMonth(List<AccountDetailVo> lists, int currentMonth, int currentYear) {
        double hour = 0;
        double minutes = 0;
        NumberFormat formatNumber = new DecimalFormat("#0.00");
        for (AccountDetailVo accountDetailVo : lists) {
            int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH) + 1;
            int year = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.YEAR);
            if (month == currentMonth && year == currentYear) {
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
     * @param currentYear:  current year.
     * @return String is list day work not full of employee.
     */
    public static String listDayWorkNotFull(List<AccountDetailVo> lists, int currentMonth, int currentYear) {
        String listDayNotFull = "";
        if (lists.isEmpty()) {
            return listDayNotFull;
        } else {
            for (AccountDetailVo accountDetailVo : lists) {
                int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH) + 1;
                int year = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.YEAR);
                if (month == currentMonth && year == currentYear) {
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
    private static Time convert(String s) throws ParseException {
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
        for (int i = 1976; i < 3000; i++) {
            lists.add(String.valueOf(i));
        }
        return lists;
    }

    public static String dispatcher(String role, String pageFirst, String pageSecond) {
        return role.equalsIgnoreCase("USER") ? pageFirst : pageSecond;
    }
}
