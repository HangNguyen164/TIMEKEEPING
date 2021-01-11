package com.tda.timekeeping.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    @Id
    private String username;

    @Column
    private String password;

    @Column
    private String role;
}
