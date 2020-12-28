package com.tda.timekeeping.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

import static com.tda.timekeeping.util.Helper.DATE_FORMAT;
import static com.tda.timekeeping.util.Helper.TIME_FORMAT;

@Entity
@Table(name = "account_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String department;

    @Column
    private String position;

    @DateTimeFormat(pattern = DATE_FORMAT)
    @Column(name = "work_date")
    private Date workDate;

    @DateTimeFormat(pattern = TIME_FORMAT)
    @Column(name = "start_time")
    private Time startTime;

    @DateTimeFormat(pattern = TIME_FORMAT)
    @Column(name = "end_time")
    private Time endTime;

    @Column
    private String note;

    @Column(name = "send_mail")
    private int checkEmail;
}
