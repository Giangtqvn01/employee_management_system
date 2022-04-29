package com.truonggiang.employee_management_system.model.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StaffByDepartmentResponse {
    private List<StaffByDepartment> staffByDepartments;
    private String departmentName;
    private String departmentCd;
    private Integer count;

    public Integer getCount() {
        return staffByDepartments.size();
    }
}
