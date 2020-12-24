package com.tda.timekeeping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table
@Getter
@Setter
@ToString
public class Account {
    @Id
    private String username;

    @Column(name = "role_id")
    private int roleID;

    private String password;
}
