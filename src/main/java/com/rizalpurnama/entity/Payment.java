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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Data
@SQLDelete(sql = "UPDATE payment SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Payment extends BaseEntity{
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_virtual_account")
    private VirtualAccount virtualAccount;

    @NotNull
    private LocalDateTime transactionTime;

    @NotNull @NotEmpty
    private String providerReference;

    @NotNull @Min(1)
    private BigDecimal amount;
}
