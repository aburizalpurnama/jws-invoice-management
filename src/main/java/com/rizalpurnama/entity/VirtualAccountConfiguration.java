package com.rizalpurnama.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity @Data
@SQLDelete(sql = "UPDATE virtual_account_configuration SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class VirtualAccountConfiguration extends BaseEntity{
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String code;

    @NotNull @NotEmpty @Size(min =  3, max = 100)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_payment_provider")
    private PaymentProvider paymentProvider;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_bank_account")
    private BankAccount bankAccount;

    @NotNull @Min(0)
    private BigDecimal transaction_fee_flat = BigDecimal.ZERO;

    @NotNull @Min(0)
    private double transaction_fee_persentage;

    @NotNull @NotEmpty @Size(min =  3, max = 3)
    private String company_prefix;

    @NotNull @NotEmpty @Min(10)
    private int accountNumberLength;
}
