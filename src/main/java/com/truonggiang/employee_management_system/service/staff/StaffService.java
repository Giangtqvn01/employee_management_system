package com.truonggiang.employee_management_system.service.staff;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.AddStaffRequest;
import com.truonggiang.employee_management_system.model.staff.GetStaffByDepartmentRequest;
import com.truonggiang.employee_management_system.model.staff.UpdateStaffRequest;
import com.truonggiang.employee_management_system.model.staff.UpdateUrlAvatarStaffRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface StaffService {
    ResponseModel addStaff(UserPrincipal userPrincipal, AddStaffRequest request);

    ResponseModel updateStaff(UserPrincipal userPrincipal, UpdateStaffRequest request);

    ResponseModel getOneStaff(UserPrincipal userPrincipal, String staffNo);

    ResponseModel updateUrlAvatarStaff(UserPrincipal userPrincipal, UpdateUrlAvatarStaffRequest request);

    ResponseModel getStaff(UserPrincipal userPrincipal, String staffRequest, Integer page, Integer size);

    ResponseModel getStaffByDepartment(UserPrincipal userPrincipal, GetStaffByDepartmentRequest request);
}
