package com.truonggiang.employee_management_system.model.employeeTimesheet;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateTimekeepingRequest {
    @NotBlank
    @SQLInjectionSafe
    private String staffNo;
    @NotNull
    private Timestamp timekeepingDate;
    @NotNull
    private Timestamp clockIn;
    @NotNull
    private Timestamp clockOut;
}
