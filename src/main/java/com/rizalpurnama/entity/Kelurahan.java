package com.rizalpurnama.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "kelurahan")
public class Kelurahan {
    @Id
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kecamatan")
    private Kecamatan kecamatan;

    @NotNull
    @NotEmpty
    private String code;

    @NotNull @NotEmpty
    private String name;
}
