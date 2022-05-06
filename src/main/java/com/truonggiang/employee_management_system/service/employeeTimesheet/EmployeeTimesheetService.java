package com.truonggiang.employee_management_system.service.employeeTimesheet;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.employeeTimesheet.CreateTimekeepingRequest;
import com.truonggiang.employee_management_system.model.employeeTimesheet.UpdateTimekeepingRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

import java.sql.Timestamp;

public interface EmployeeTimesheetService {
    ResponseModel createTimekeeping(UserPrincipal userPrincipal, CreateTimekeepingRequest request);

    ResponseModel checkInEmployee(UserPrincipal userPrincipal, String staffNo);

    ResponseModel checkOutEmployee(UserPrincipal userPrincipal, Integer employeeTimesheetId);

    ResponseModel updateTimekeeping(UserPrincipal userPrincipal, UpdateTimekeepingRequest request);

    ResponseModel deleteTimekeeping(UserPrincipal userPrincipal, Integer employeeTimesheetId);

    ResponseModel getOneTimekeeping(UserPrincipal userPrincipal, Timestamp timekeepingDate);

    ResponseModel checkInOrOutAndroid(UserPrincipal userPrincipal);

    ResponseModel getTimekeeping(UserPrincipal userPrincipal, Timestamp timekeepingDate);
}
