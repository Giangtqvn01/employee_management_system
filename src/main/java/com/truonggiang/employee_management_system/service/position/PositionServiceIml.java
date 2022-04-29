package com.truonggiang.employee_management_system.service.position;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.department.CreateDepartmentRequest;
import com.truonggiang.employee_management_system.model.department.UpdateDepartmentRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceIml implements PositionService{
    @Override
    public ResponseModel createPosition(UserPrincipal userPrincipal, CreateDepartmentRequest request) {
        return null;
    }

    @Override
    public ResponseModel updatePosition(UserPrincipal userPrincipal, UpdateDepartmentRequest request) {
        return null;
    }

    @Override
    public ResponseModel getPosition(UserPrincipal userPrincipal, Integer status, Integer activeFlg,
                                     String departmentName) {
        return null;
    }
}
