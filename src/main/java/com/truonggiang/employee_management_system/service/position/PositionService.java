package com.truonggiang.employee_management_system.service.position;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.department.CreateDepartmentRequest;
import com.truonggiang.employee_management_system.model.department.UpdateDepartmentRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface PositionService {
    ResponseModel createPosition(UserPrincipal userPrincipal, CreateDepartmentRequest request);

    ResponseModel updatePosition(UserPrincipal userPrincipal, UpdateDepartmentRequest request);

    ResponseModel getPosition(UserPrincipal userPrincipal, Integer status, Integer activeFlg, String departmentName);
}
