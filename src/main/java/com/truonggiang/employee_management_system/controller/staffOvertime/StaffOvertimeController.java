package com.truonggiang.employee_management_system.controller.staffOvertime;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.AddStaffRequest;
import com.truonggiang.employee_management_system.model.staff.UpdateStaffRequest;
import com.truonggiang.employee_management_system.model.staffOvertime.AddStaffOvertimeRequest;
import com.truonggiang.employee_management_system.model.staffOvertime.UpdateStaffOvertimeRequest;
import com.truonggiang.employee_management_system.security.CurrentUser;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.service.staff.StaffService;
import com.truonggiang.employee_management_system.service.staffOvertime.StaffOvertimeService;
import com.truonggiang.employee_management_system.utils.Constant;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/staff-overtime")
public class StaffOvertimeController {
    private static Logit log = Logit.getInstance(StaffOvertimeController.class);

    @Autowired
    private StaffOvertimeService staffOvertimeService;

    @PostMapping()
    public ResponseEntity<?> addStaffOvertime(@CurrentUser UserPrincipal userPrincipal,
                                      @RequestBody @Valid AddStaffOvertimeRequest request) {
        log.info("Add staff overtime =" + request.getStaffNo());
        long start = System.currentTimeMillis();
        ResponseModel model = staffOvertimeService.addStaffOvertime(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @PutMapping()
    public ResponseEntity<?> updateStaffOvertime(@CurrentUser UserPrincipal userPrincipal,
                                         @RequestBody @Valid UpdateStaffOvertimeRequest request) {
        log.info("Update staff overtime");
        long start = System.currentTimeMillis();
        ResponseModel model = staffOvertimeService.updateStaffOvertime(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @GetMapping("/get-staff-overtime")
    public ResponseEntity<?> getStaffOvertime(@CurrentUser UserPrincipal userPrincipal,
                                      @RequestParam(value = "staff_request",required = false) @SQLInjectionSafe String staffRequest,
                                      @RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size
    ) {
        if (page == null || page <= 0) page = Constant.PAGINATION.DEFAULT_PAGE;
        if (size == null) size = Constant.PAGINATION.DEFAULT_PAGE_SIZE;
        log.info("Get staff overtime request "+staffRequest );
        long start = System.currentTimeMillis();
        ResponseModel responseModel = staffOvertimeService.getStaffOvertime(userPrincipal,staffRequest, page,size );
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + ", " + responseModel.getDescription() + ", time= " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }
}

