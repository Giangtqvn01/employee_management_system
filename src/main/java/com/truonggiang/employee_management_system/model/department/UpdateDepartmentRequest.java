package com.truonggiang.employee_management_system.model.department;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateDepartmentRequest {

    @NotNull
    private Integer departmentId;
    @NotBlank

    private String departmentName;
    @NotNull
    private Integer activeFlg;
    @NotNull
    private Integer status;

    private String description;
}
