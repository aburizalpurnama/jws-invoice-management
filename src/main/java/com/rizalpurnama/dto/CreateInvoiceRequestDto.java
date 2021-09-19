package com.rizalpurnama.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateInvoiceRequestDto {

    @NotNull @NotEmpty
    private String customerCode;

    @NotNull @NotEmpty
    private String invoiceTypeCode;

    @NotNull @NotEmpty
    private String description;

    @NotNull @Min(0)
    private BigDecimal amount;
}
