package com.rizalpurnama.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity @Data
public class ActivityLog {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull @Enumerated(EnumType.STRING)
    private Feature feature;

    @NotNull
    private LocalDateTime activityTime = LocalDateTime.now();

    @NotNull @Size(max = 255)
    private String message;
}
