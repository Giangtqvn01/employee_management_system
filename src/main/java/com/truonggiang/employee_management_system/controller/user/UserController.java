package com.truonggiang.employee_management_system.controller.user;

import com.truonggiang.employee_management_system.controller.auth.AuthController;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.user.ChangePasswordRequest;
import com.truonggiang.employee_management_system.security.CurrentUser;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.service.user.UserService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static Logit log = Logit.getInstance(AuthController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody @Valid ChangePasswordRequest request) {
        log.info("Change password name =" + userPrincipal.getUsername());
        long start = System.currentTimeMillis();
        ResponseModel model = userService.changePassword(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @GetMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@CurrentUser UserPrincipal userPrincipal,
                                           @RequestParam("user_id") Integer userId) {
        log.info("Change password name =" + userPrincipal.getUsername());
        long start = System.currentTimeMillis();
        ResponseModel model = userService.resetPassword(userPrincipal, userId);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
}
