package com.truonggiang.employee_management_system.model.staffOvertime;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddStaffOvertimeRequest {
    @NotBlank
    private String staffNo;
    private Timestamp overtimeDate;
    private Timestamp overtimeStart;
    private Timestamp overtimeEnds;
    @NotBlank
    private String description;
}
