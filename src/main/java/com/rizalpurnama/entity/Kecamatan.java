package com.rizalpurnama.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Table(name = "kecamatan")
public class Kecamatan {
    @Id
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kota_kab")
    private KotaKabupaten kotaKabupaten;

    @NotNull
    @NotEmpty
    private String code;

    @NotNull @NotEmpty
    private String name;
}
