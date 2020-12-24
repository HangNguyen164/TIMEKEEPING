package com.tda.timekeeping.util;

import com.tda.timekeeping.vo.AccountDetailVo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {
    private static final int HOUR_LUNCH = 1;
    private static final int MINUTES_LUNCH = 30;
    private static final int MINUTES_TO_HOUR = 60;

    public static int getTypeOfDate(Date date, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(type);
    }

    public static String formatDate(Date date, String typeFormat) {
        if (date != null) {
            SimpleDateFormat sdfDate = new SimpleDateFormat(typeFormat);
            return sdfDate.format(date);
        }
        return "";
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

    public static int totalNotWorkInOffice(List<AccountDetailVo> lists, int currentMonth) {
        int total = 0;
        for (AccountDetailVo accountDetailVo : lists) {
            int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH);
            if (month == currentMonth && accountDetailVo.getSendEmail() != 0) {
                if (!accountDetailVo.getHour().isEmpty()) {
                    long hour = Long.valueOf(accountDetailVo.getHour().split(":")[0]);
                    if (hour == 0) {
                        total++;
                    }
                }
            }
        }
        return total;
    }

    public static String totalWorkInMonth(List<AccountDetailVo> lists, int currentMonth) {
        double hour = 0;
        double minutes = 0;
        NumberFormat formatNumber = new DecimalFormat("#0.00");
        for (AccountDetailVo accountDetailVo : lists) {
            int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH);
            if (month == currentMonth) {
                if (!accountDetailVo.getHour().isEmpty()) {
                    hour += Long.valueOf(accountDetailVo.getHour().split(":")[0]);
                    minutes += Long.valueOf(accountDetailVo.getHour().split(":")[1]);
                }
            }
        }
        return formatNumber.format(hour + minutes / MINUTES_TO_HOUR);
    }

    public static String listDayWorkNotFull(List<AccountDetailVo> lists, int currentMonth) {
        String listDayNotFull = "";
        if (lists.isEmpty()) {
            return listDayNotFull;
        } else {
            for (AccountDetailVo accountDetailVo : lists) {
                int month = getTypeOfDate(accountDetailVo.getWorkDate(), Calendar.MONTH);
                if (month == currentMonth) {
                    if (!accountDetailVo.getHour().isEmpty()) {
                        long hour = Long.valueOf(accountDetailVo.getHour().split(":")[0]);
                        if (hour < 8) {
                            listDayNotFull += accountDetailVo.getWorkDateStr() + ",";
                        }
                    }
                }
            }
        }
        return listDayNotFull.length() > 0 ? listDayNotFull.substring(0, listDayNotFull.length() - 1) : "";
    }
}