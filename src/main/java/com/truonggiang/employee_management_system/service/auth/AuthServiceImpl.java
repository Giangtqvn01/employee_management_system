package com.truonggiang.employee_management_system.service.auth;

import com.truonggiang.employee_management_system.entity.Role;
import com.truonggiang.employee_management_system.entity.RoleName;
import com.truonggiang.employee_management_system.entity.User;
import com.truonggiang.employee_management_system.exception.AppException;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.auth.RegisterRequest;
import com.truonggiang.employee_management_system.repository.UserRepository;
import com.truonggiang.employee_management_system.repository.role.RoleRepository;
import com.truonggiang.employee_management_system.utils.Constant;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseModel registerUser(RegisterRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message = "";
            request = (RegisterRequest) HtmlUtil.validateRequest(request);

            User newAccount = new User();
            newAccount.setNumberCode(request.getNumberCode());
            newAccount.setPassword(passwordEncoder.encode(request.getPassword()));
            Role userRole = roleRepository.findByRoleCd(RoleName.ADMIN)
                    .orElseThrow(() -> new AppException("User Role not set."));
            newAccount.setRoles(Collections.singleton(userRole));
            newAccount.setFullName(request.getFullName());
            newAccount.setUserName(request.getUserName());
            userRepository.save(newAccount);

            message = "Created account success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(success);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;

        } catch (Exception e) {
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error!");
            ResponseModel model = new ResponseModel();
            model.setData(error);
            model.setDescription("Server error!");
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    public User getAccountInfoByLoginId(String userName) {
        return userRepository.findByUserNameAndActiveFlg(userName, Constant.ACTIVE_FLG.NOT_DELETE).orElse(null);
    }
}
