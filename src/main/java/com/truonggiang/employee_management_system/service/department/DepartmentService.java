package com.truonggiang.employee_management_system.service.department;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.department.CreateDepartmentRequest;
import com.truonggiang.employee_management_system.model.department.UpdateDepartmentRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface DepartmentService {
    ResponseModel createDepartment(UserPrincipal userPrincipal, CreateDepartmentRequest request);

    ResponseModel updateDepartment(UserPrincipal userPrincipal, UpdateDepartmentRequest request);

    ResponseModel getDepartment(UserPrincipal userPrincipal, Integer status, Integer activeFlg, String departmentName);
}
