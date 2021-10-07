package com.rizalpurnama.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "kota_kabupaten")
public class KotaKabupaten {
    @Id
    private String id;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_provinsi")
    private Provinsi provinsi;

    @NotNull
    @NotEmpty
    private String code;

    @NotNull @NotEmpty
    private String name;
}
