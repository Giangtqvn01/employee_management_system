package com.truonggiang.employee_management_system.service.staffOvertime;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staffOvertime.AddStaffOvertimeRequest;
import com.truonggiang.employee_management_system.model.staffOvertime.UpdateStaffOvertimeRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface StaffOvertimeService {
    ResponseModel addStaffOvertime(UserPrincipal userPrincipal, AddStaffOvertimeRequest request);

    ResponseModel updateStaffOvertime(UserPrincipal userPrincipal, UpdateStaffOvertimeRequest request);

    ResponseModel getStaffOvertime(UserPrincipal userPrincipal, String staffRequest, Integer page, Integer size);
}
