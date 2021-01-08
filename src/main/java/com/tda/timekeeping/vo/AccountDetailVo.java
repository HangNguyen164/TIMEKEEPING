package com.tda.timekeeping.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

import static com.tda.timekeeping.util.Helper.TIME_FORMAT;
import static com.tda.timekeeping.util.Helper.DATE_FORMAT;
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

    @DateTimeFormat(pattern = DATE_FORMAT)
    private Date workDate;

    @DateTimeFormat(pattern = TIME_FORMAT)
    private Date startTime;

    @DateTimeFormat(pattern = TIME_FORMAT)
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
