package com.truonggiang.employee_management_system.service.user;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.user.ChangePasswordRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface UserService {
    ResponseModel changePassword(UserPrincipal userPrincipal, ChangePasswordRequest request);

    ResponseModel resetPassword(UserPrincipal userPrincipal, Integer userId);
}
