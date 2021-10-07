package com.rizalpurnama.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity @Data
@Table(name = "provinsi")
public class Provinsi {

    @Id
    private String id;

    @NotNull @NotEmpty
    private String code;

    @NotNull @NotEmpty
    private String name;
}
