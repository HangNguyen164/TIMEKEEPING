package com.entity;

import com.constants.RoleConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = RoleConstant.TABLE_NAME)
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = RoleConstant.ID)
    private int id;

    @Column(name = RoleConstant.NAME, unique = true)
    private String name;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }
}
