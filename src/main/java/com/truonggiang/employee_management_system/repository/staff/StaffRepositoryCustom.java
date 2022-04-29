package com.truonggiang.employee_management_system.repository.staff;

import com.truonggiang.employee_management_system.model.staff.GetStaffByDepartmentRequest;
import com.truonggiang.employee_management_system.model.staff.StaffByDepartment;

import java.util.List;

public interface StaffRepositoryCustom {
    List<StaffByDepartment> getStaffByDepartment(GetStaffByDepartmentRequest request);
}
