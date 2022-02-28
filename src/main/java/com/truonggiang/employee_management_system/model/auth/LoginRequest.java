package com.truonggiang.employee_management_system.model.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginRequest {
    @NotBlank
    @SQLInjectionSafe
    private String userName;
    @NotBlank
    @Min(6)
    @SQLInjectionSafe
    private String password;
}
