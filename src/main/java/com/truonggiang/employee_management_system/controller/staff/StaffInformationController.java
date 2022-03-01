package com.truonggiang.employee_management_system.controller.staff;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.StaffInformationRequest;
import com.truonggiang.employee_management_system.security.CurrentUser;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.service.staff.StaffInformationService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/staff-information")
public class StaffInformationController {
    private static Logit log = Logit.getInstance(StaffController.class);
    @Autowired
    private StaffInformationService staffInformationService;

    @PostMapping("/update-or-create")
    public ResponseEntity<?> createOrUpdateStaffInformation(@CurrentUser UserPrincipal userPrincipal,
                                                            @RequestBody @Valid StaffInformationRequest request ){
        log.info("Create or update  staff information");
        long start = System.currentTimeMillis();
        ResponseModel model = staffInformationService.createOrUpdateStaffInformation(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @GetMapping("/get-one")
    public ResponseEntity<?> getOneStaffInformation(@CurrentUser UserPrincipal userPrincipal,
                                                            @RequestParam("staff_no") @SQLInjectionSafe String staffNo ){
        log.info("Get one staff information");
        long start = System.currentTimeMillis();
        ResponseModel model = staffInformationService.getOneStaffInformation(userPrincipal, staffNo);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
}
