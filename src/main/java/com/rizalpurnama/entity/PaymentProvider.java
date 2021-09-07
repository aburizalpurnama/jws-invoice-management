package com.rizalpurnama.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@SQLDelete(sql = "UPDATE payment_provider SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class PaymentProvider extends BaseEntity{
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String code;

    @NotNull @NotEmpty @Size(min =  3, max = 100)
    private String name;

    private String logo;
}
