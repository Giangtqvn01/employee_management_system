package com.truonggiang.employee_management_system.service.staff;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.StaffInformationRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface StaffInformationService {
    ResponseModel createOrUpdateStaffInformation(UserPrincipal userPrincipal, StaffInformationRequest request);

    ResponseModel getOneStaffInformation(UserPrincipal userPrincipal, String staffNo);
}
