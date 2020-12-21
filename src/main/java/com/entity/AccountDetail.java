package com.entity;

import com.constants.AccountDetailConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = AccountDetailConstant.TABLE_NAME)
@Getter
@Setter
public class AccountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;

    private String name;

    private String department;

    private String position;

    @Column(name = AccountDetailConstant.WORK_DATE)
    private Date workDate;

    @Column(name = AccountDetailConstant.START_TIME)
    private Date startTime;

    @Column(name = AccountDetailConstant.END_TIME)
    private Date endTime;

    private String note;

    @Column(name = AccountDetailConstant.SEND_MAIL)
    private int checkEmail;
}
