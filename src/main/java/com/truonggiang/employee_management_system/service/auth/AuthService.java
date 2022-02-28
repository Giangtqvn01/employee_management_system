package com.truonggiang.employee_management_system.service.auth;

import com.truonggiang.employee_management_system.entity.User;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.auth.RegisterRequest;

public interface AuthService {
    ResponseModel registerUser(RegisterRequest request);

    User getAccountInfoByLoginId(String userName);
}
