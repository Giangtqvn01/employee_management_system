package com.truonggiang.employee_management_system.service.staff;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.AddStaffRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface StaffService {
    ResponseModel addStaff(UserPrincipal userPrincipal, AddStaffRequest request);
}
