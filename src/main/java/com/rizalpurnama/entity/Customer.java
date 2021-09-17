package com.rizalpurnama.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity @Data
@SQLDelete(sql = "UPDATE customer SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Customer extends BaseEntity{

    @NotNull @NotEmpty @Size(max = 100)
    private String code;

    @NotNull @NotEmpty @Size(max = 255)
    private String name;

    @NotNull @NotEmpty @Size(max = 100)
    @Email
    private String email;

    @NotNull @NotEmpty @Size(max = 30)
    private String mobilePhone;


}
