package com.truonggiang.employee_management_system.controller.employeeTimesheet;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.employeeTimesheet.CreateTimekeepingRequest;
import com.truonggiang.employee_management_system.model.employeeTimesheet.UpdateTimekeepingRequest;
import com.truonggiang.employee_management_system.security.CurrentUser;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.service.employeeTimesheet.EmployeeTimesheetService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/employee-timesheet")
public class EmployeeTimesheetController {
    private final Logit log = Logit.getInstance(EmployeeTimesheetController.class);

    @Autowired
    private EmployeeTimesheetService employeeTimesheetService;

    @PostMapping("/create-timekeeping")
    public ResponseEntity<?> createTimekeeping(@CurrentUser UserPrincipal userPrincipal,
                                               @RequestBody @Valid CreateTimekeepingRequest request) {
        log.info("Create timekeeping ");
        long start = System.currentTimeMillis();
        ResponseModel model = employeeTimesheetService.createTimekeeping(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @PostMapping("/update-timekeeping")
    public ResponseEntity<?> updateTimekeeping(@CurrentUser UserPrincipal userPrincipal,
                                               @RequestBody @Valid UpdateTimekeepingRequest request) {
        log.info("Create timekeeping ");
        long start = System.currentTimeMillis();
        ResponseModel model = employeeTimesheetService.updateTimekeeping(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }


    @GetMapping("/check-in")
    public ResponseEntity<?> checkInEmployee(@CurrentUser UserPrincipal userPrincipal,
                                             @RequestParam("staff_no") @SQLInjectionSafe String staffNo) {
        log.info("Check in staff ");
        long start = System.currentTimeMillis();
        ResponseModel model = employeeTimesheetService.checkInEmployee(userPrincipal, staffNo);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @GetMapping("/check-out")
    public ResponseEntity<?> checkOutEmployee(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestParam("employee_timesheet_id") Integer employeeTimesheetId) {
        log.info("Check out staff ");
        long start = System.currentTimeMillis();
        ResponseModel model = employeeTimesheetService.checkOutEmployee(userPrincipal, employeeTimesheetId);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @GetMapping("/delete-timekeeping")
    public ResponseEntity<?> deleteTimekeeping(@CurrentUser UserPrincipal userPrincipal,
                                               @RequestParam("employee_timesheet_id") Integer employeeTimesheetId) {
        log.info("Check out staff ");
        long start = System.currentTimeMillis();
        ResponseModel model = employeeTimesheetService.deleteTimekeeping(userPrincipal, employeeTimesheetId);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @GetMapping("/get-one-timekeeping")
    public ResponseEntity<?> getOneTimekeeping(@CurrentUser UserPrincipal userPrincipal,
                                               @RequestParam("timekeeping_date")Timestamp timekeepingDate){
        log.info("Get one timekeeping "+timekeepingDate);
        long start = System.currentTimeMillis();
        ResponseModel model = employeeTimesheetService.getOneTimekeeping(userPrincipal, timekeepingDate);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
    @GetMapping("/check-in-out-android")
    public ResponseEntity<?> checkInOrOutAndroid(@CurrentUser UserPrincipal userPrincipal){
        log.info("Check in or out android");
        long start = System.currentTimeMillis();
        ResponseModel model = employeeTimesheetService.checkInOrOutAndroid(userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
}
