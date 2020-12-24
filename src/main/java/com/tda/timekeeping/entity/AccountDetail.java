package com.tda.timekeeping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "account_detail")
@Getter
@Setter
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

    @Column(name = "work_date")
    private Date workDate;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column
    private String note;

    @Column(name = "send_mail")
    private int checkEmail;
}
