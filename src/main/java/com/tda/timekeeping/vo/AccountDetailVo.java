package com.tda.timekeeping.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;

import static com.tda.timekeeping.util.Helper.formatDate;
import static com.tda.timekeeping.util.Helper.getTypeOfDate;
import static com.tda.timekeeping.util.Helper.setTimeWorkInDay;


@Getter
@Setter
@ToString
public class AccountDetailVo {
    private String username;
    private String name;
    private String department;
    private String position;
    private Date workDate;
    private int day;
    private Date startTime;
    private Date endTime;
    private String startTimeStr;
    private String endTimeStr;
    private String workDateStr;
    private String hour;
    private String note;
    private int sendEmail;

    public AccountDetailVo(String username, String name, String department, String position, Date workDate, Date startTime, Date endTime, String note, int sendEmail) {
        this.username = username;
        this.name = name;
        this.department = department;
        this.position = position;
        this.workDate = workDate;
        this.workDateStr = formatDate(workDate, "yyyy-MM-dd");
        this.day = getTypeOfDate(workDate, Calendar.DAY_OF_WEEK);
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTimeStr = formatDate(startTime, "hh:mm");
        this.endTimeStr = formatDate(endTime, "hh:mm");
        this.hour = setTimeWorkInDay(startTime, endTime);
        this.note = note;
        this.sendEmail = sendEmail;
    }
}
