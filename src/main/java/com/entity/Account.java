package com.entity;

import com.constants.AccountConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = AccountConstant.TABLE_NAME)
@Getter
@Setter
@ToString
public class Account {
    @Id
    @Column(name = AccountConstant.USERNAME)
    private String username;

    @Column(name = AccountConstant.ROLE_ID)
    private int roleID;

    @Column(name = AccountConstant.PASSWORD)
    private String password;
}
