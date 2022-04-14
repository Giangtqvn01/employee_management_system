package com.truonggiang.employee_management_system.model.staffOvertime;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateStaffOvertimeRequest {

    private Integer staffOvertimeId;
    private Timestamp overtimeDate;
    private Timestamp overtimeStart;
    private Timestamp overtimeEnds;
    @NotBlank
    @SQLInjectionSafe
    private String description;
    private Integer status;
    private Integer activeFlg;
}
