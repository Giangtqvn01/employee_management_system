package com.truonggiang.employee_management_system.controller.position;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import com.truonggiang.employee_management_system.controller.department.DepartmentController;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.department.CreateDepartmentRequest;
import com.truonggiang.employee_management_system.model.department.UpdateDepartmentRequest;
import com.truonggiang.employee_management_system.security.CurrentUser;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.service.department.DepartmentService;
import com.truonggiang.employee_management_system.service.position.PositionService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/position")
public class PositionController {
    private final Logit log = Logit.getInstance(PositionController.class);

    @Autowired
    private PositionService positionService;

    @PostMapping()
    public ResponseEntity<?> createPosition(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody @Valid CreateDepartmentRequest request) {
        log.info("Create department ");
        long start = System.currentTimeMillis();
        ResponseModel model = positionService.createPosition(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @PutMapping()
    public ResponseEntity<?> updatePosition(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody @Valid UpdateDepartmentRequest request) {
        log.info("Update department ");
        long start = System.currentTimeMillis();
        ResponseModel model = positionService.updatePosition(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @GetMapping
    public ResponseEntity<?> getPosition(@CurrentUser UserPrincipal userPrincipal,
                                           @RequestParam("status") Integer status,
                                           @RequestParam("activeFlg") Integer activeFlg,
                                           @RequestParam(value = "departmentName", required = false) @SQLInjectionSafe
                                                   String departmentName) {
        log.info("Get department ");
        long start = System.currentTimeMillis();
        ResponseModel model = positionService.getPosition(userPrincipal, status, activeFlg, departmentName);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
}
