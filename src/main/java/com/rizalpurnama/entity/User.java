package com.rizalpurnama.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @Entity
@Table(name = "s_users")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty
    private String username;

    @NotNull
    private Boolean active = false;

    @ManyToOne
    @JoinColumn(name = "id_role")
    @NotNull
    private Role role;
}
