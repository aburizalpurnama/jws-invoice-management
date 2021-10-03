package com.rizalpurnama.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterFormDto {

    @NotNull @NotEmpty @Size(min = 3, max = 255)
    private String name;

    @NotNull @NotEmpty @Email
    private String email;

    @NotNull @NotEmpty @Size(min = 3, max = 20)
    private String phone;
}
