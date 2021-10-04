package com.rizalpurnama.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data @Entity
@Table(name = "reset_password")
public class ResetPassword {
    private static final Integer RESET_PASSWORD_EXPIRED_DAYS =15;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    private LocalDateTime created = LocalDateTime.now();

    @NotNull
    private LocalDateTime expired = LocalDateTime.now()
            .plusDays(RESET_PASSWORD_EXPIRED_DAYS);

    @NotNull
    @ManyToOne @JoinColumn(name = "id_user")
    private User user;

    @NotNull @NotEmpty
    private String uniqueCode = UUID.randomUUID().toString();
}
