package com.truonggiang.employee_management_system.model.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StaffByDepartment {
    private String departmentName;
    private String departmentCd;
    private Integer staffId;
    private String staffNo;
    private String lastName;
    private String firstName;
    private String phoneNo;
    private String gender;
    private String positionName;
    private String smLastName;
    private String smFirstName;
    private Integer sActiveFlg;
    private Integer dActiveFlg;
    private Integer pActiveFlg;

}
