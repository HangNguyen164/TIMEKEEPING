package com.tda.timekeeping.vo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

import static com.tda.timekeeping.util.Helper.getTypeOfDate;
import static com.tda.timekeeping.util.Helper.setTimeWorkInDay;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDetailVo {
    private int id;
    private String username;

    private String name;

    private String department;

    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    @DateTimeFormat(pattern = "hh:mm")
    private Date startTime;

    @DateTimeFormat(pattern = "hh:mm")
    private Date endTime;

    private String note;

    private int sendEmail;

    public int getDay() {
        return getTypeOfDate(workDate, Calendar.DAY_OF_WEEK);
    }

    public String getHour() {
        return setTimeWorkInDay(startTime, endTime);
    }

    public boolean hourIsEmpty() {
        return getHour().isEmpty() || getHour() == null;
    }
}
