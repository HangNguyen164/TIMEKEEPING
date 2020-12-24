package com.tda.timekeeping.vo;

import lombok.*;

import java.util.Calendar;
import java.util.Date;

import static com.tda.timekeeping.util.Helper.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDetailVo {
    private String username;
    private String name;
    private String department;
    private String position;
    private Date workDate;
    private Date startTime;
    private Date endTime;
    private String note;
    private int sendEmail;

    public String getWorkDateStr() {
        return formatDate(workDate, "yyyy-MM-dd");
    }

    public int getDay() {
        return getTypeOfDate(workDate, Calendar.DAY_OF_WEEK);
    }

    public String getStartTimeStr() {
        return formatDate(startTime, "hh:mm aa");
    }

    public String getEndTimeStr() {
        return formatDate(endTime, "hh:mm aa");
    }

    public String getHour() {
        return setTimeWorkInDay(startTime, endTime);
    }

}
