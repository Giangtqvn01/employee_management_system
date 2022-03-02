package com.truonggiang.employee_management_system.model.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddStaffRequest {
    @NotBlank
    @SQLInjectionSafe
    private String firstName;
    @NotBlank
    @SQLInjectionSafe
    private String lastName;
    @NotBlank
    @SQLInjectionSafe
    private String phoneNo;
    @NotNull
    private Integer departmentId;
    @NotNull
    private Integer positionId;
    @NotNull
    @SQLInjectionSafe
    private String salaryStaff;
    @NotNull
    private Integer gender;
    @NotNull
    private Timestamp contractDate;
    @NotBlank
    @SQLInjectionSafe
    private String urlAvatar;
}
