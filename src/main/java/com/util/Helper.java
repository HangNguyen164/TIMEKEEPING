package com.util;


import com.vo.AccountDetailVo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {
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

    public static String setTImeWorkInDay(Date startTime, Date endTime) {
        if (startTime != null && endTime != null) {
            long diff = endTime.getTime() - startTime.getTime();
            long hour = TimeUnit.MILLISECONDS.toHours(diff) - 1;
            long minutes = (TimeUnit.MILLISECONDS.toMinutes(diff) % 60) - 30;
            if (minutes < 0) {
                hour--;
                minutes = 60 - Math.abs(minutes);
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
        return formatNumber.format(hour + minutes / 60);
    }

    public static String listDayWorkNotFull(List<AccountDetailVo> lists, int currentMonth) {
        String listDayNotFull = "";
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
        return listDayNotFull.substring(0, listDayNotFull.length() - 1);
    }
}
