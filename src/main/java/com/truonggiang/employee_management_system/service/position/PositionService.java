package com.truonggiang.employee_management_system.service.position;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.position.CreatePositionRequest;
import com.truonggiang.employee_management_system.model.position.UpdatePositionRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface PositionService {
    ResponseModel createPosition(UserPrincipal userPrincipal, CreatePositionRequest request);

    ResponseModel updatePosition(UserPrincipal userPrincipal, UpdatePositionRequest request);

    ResponseModel getPosition(UserPrincipal userPrincipal, Integer status, Integer activeFlg, Integer departmentId,String positionName);
}
