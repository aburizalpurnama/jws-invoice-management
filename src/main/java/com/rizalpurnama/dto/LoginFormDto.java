package com.rizalpurnama.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginFormDto {

    @NotNull @NotEmpty @Size(min = 3, max = 255)
    private String username;

    @NotNull @NotEmpty
    private String password;
}
