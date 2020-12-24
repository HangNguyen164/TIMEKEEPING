package com.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static com.util.Helper.*;

@Getter
@Setter
@ToString
public class AccountDetailVo {
    private int id;
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

    public AccountDetailVo(int id, String username, String name, String department, String position, Date workDate, Date startTime, Date endTime, String note, int sendEmail) {
        this.id = id;
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
        this.hour = setTImeWorkInDay(startTime, endTime);
        this.note = note;
        this.sendEmail = sendEmail;
    }
}
