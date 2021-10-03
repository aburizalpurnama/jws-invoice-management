package com.rizalpurnama.entity;

import lombok.Data;

import javax.persistence.*;

@Data @Entity
@Table(name = "s_users_passwords")
public class UserPassword {

    @Id @Column(name = "id_user")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_user")
    private User user;
    private String password;
}
