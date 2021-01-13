package com.tda.timekeeping.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

import org.springframework.format.annotation.DateTimeFormat;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
