package com.truonggiang.employee_management_system.model.department;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateDepartmentRequest {
    @NotBlank
    @SQLInjectionSafe
    private String departmentName;
    @SQLInjectionSafe
    @NotBlank
    private String departmentCd;
    @SQLInjectionSafe
    private String description;

}
