package com.tda.timekeeping.entity;

import lombok.*;

import javax.persistence.*;

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

    private String password;

    //    @Column(name = "role_id")
//    private int roleID;
    // Many to One Có nhiều người ở 1 địa điểm.
    @ManyToOne
    @JoinColumn(name = "role_id") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

    //
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
