package com.truonggiang.employee_management_system.service.user;

import com.truonggiang.employee_management_system.entity.User;
import com.truonggiang.employee_management_system.exception.BadRequestException;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.user.ChangePasswordRequest;
import com.truonggiang.employee_management_system.repository.UserRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.Constant;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseModel changePassword(UserPrincipal userPrincipal, ChangePasswordRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            request = (ChangePasswordRequest) HtmlUtil.validateRequest(request);
            User user = userRepository.findByUserId(userPrincipal.getUserId())
                    .orElseThrow(() -> new BadRequestException("Not found account!"));
            String password = passwordEncoder.encode(request.getPassword());
            if (!passwordEncoder.matches(password, user.getPassword())) {
                message = "Update password failed! Wrong password or username. ";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setLoginFistTime(Constant.LOGIN.MANY_TIMES);
            userRepository.save(user);

            message = "Update password success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(success);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error!");
            ResponseModel model = new ResponseModel();
            model.setData(error);
            model.setDescription("Server error!");
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    public ResponseModel resetPassword(UserPrincipal userPrincipal, Integer userId) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            
            User user = userRepository.findByUserId(userId)
                    .orElseThrow(() -> new BadRequestException("Not found account!"));

            user.setPassword(passwordEncoder.encode(Constant.PASSWORD.DEFAULT));
            user.setLoginFistTime(Constant.LOGIN.FIRST);
            userRepository.save(user);

            message = "Reset password success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(success);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error!");
            ResponseModel model = new ResponseModel();
            model.setData(error);
            model.setDescription("Server error!");
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }
}
