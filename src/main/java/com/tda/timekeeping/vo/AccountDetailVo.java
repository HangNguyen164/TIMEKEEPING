package com.tda.timekeeping.vo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.tda.timekeeping.util.Helper.*;

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
        return formatTime(startTime, endTime);
    }

    public boolean hourIsEmpty() {
        return getHour().isEmpty() || getHour() == null;
    }

}
