package com.tda.timekeeping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

import java.sql.Date;
import java.sql.Time;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "work_date")
    private Date workDate;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name = "start_time")
    private Time startTime;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name = "end_time")
    private Time endTime;

    @Column
    private String note;

    @Column(name = "send_mail")
    private int checkEmail;
}
