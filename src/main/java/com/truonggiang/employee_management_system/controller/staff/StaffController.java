package com.truonggiang.employee_management_system.controller.staff;

import com.truonggiang.employee_management_system.controller.auth.AuthController;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.AddStaffRequest;
import com.truonggiang.employee_management_system.model.user.ChangePasswordRequest;
import com.truonggiang.employee_management_system.security.CurrentUser;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.service.staff.StaffService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private static Logit log = Logit.getInstance(AuthController.class);

    @Autowired
    private StaffService staffService;

    @PostMapping("/add-staff")
    public ResponseEntity<?> addStaff(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody @Valid AddStaffRequest request) {
        log.info("Change password name =" + userPrincipal.getUsername());
        long start = System.currentTimeMillis();
        ResponseModel model = staffService.addStaff(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
}
