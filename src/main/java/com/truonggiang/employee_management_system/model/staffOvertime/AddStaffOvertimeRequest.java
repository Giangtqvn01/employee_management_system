package com.truonggiang.employee_management_system.model.staffOvertime;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddStaffOvertimeRequest {
    @NotBlank
    @SQLInjectionSafe
    private String staffNo;
    private Timestamp overtimeDate;
    private Timestamp overtimeStart;
    private Timestamp overtimeEnds;
    @NotBlank
    @SQLInjectionSafe
    private String description;
}
