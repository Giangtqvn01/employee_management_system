package com.truonggiang.employee_management_system.model.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChangePasswordRequest {
    @NotBlank
    @Min(5)

    private String password;
    @NotBlank
    @Min(5)

    private String newPassword;
}
