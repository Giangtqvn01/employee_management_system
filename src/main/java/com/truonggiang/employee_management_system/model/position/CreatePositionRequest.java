package com.truonggiang.employee_management_system.model.position;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreatePositionRequest {
    @NotNull
    private Integer departmentId;
    @NotBlank

    private String positionName;
    @NotBlank

    private String positionCd;

    private String description;
}
