package com.rizalpurnama.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity @Data
@SQLDelete(sql = "UPDATE bank_account SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class BankAccount extends BaseEntity{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;

    @NotNull @NotEmpty @Size(min = 3, max = 36)
    private String account_number;

    @NotNull @NotEmpty @Size(min = 3, max = 255)
    private String account_name;

    @NotNull @NotEmpty @Size(min = 3, max =255)
    private String branch_name;
}
