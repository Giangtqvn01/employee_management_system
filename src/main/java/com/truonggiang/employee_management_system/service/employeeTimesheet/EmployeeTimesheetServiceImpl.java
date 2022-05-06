package com.truonggiang.employee_management_system.service.employeeTimesheet;

import com.truonggiang.employee_management_system.entity.EmployeeTimesheet;
import com.truonggiang.employee_management_system.exception.AppException;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.employeeTimesheet.CreateTimekeepingRequest;
import com.truonggiang.employee_management_system.model.employeeTimesheet.UpdateTimekeepingRequest;
import com.truonggiang.employee_management_system.repository.employeeTimesheet.EmployeeTimesheetRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.Common;
import com.truonggiang.employee_management_system.utils.Constant;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeTimesheetServiceImpl implements EmployeeTimesheetService {
    @Autowired
    private EmployeeTimesheetRepository employeeTimesheetRepository;

    @Override
    public ResponseModel createTimekeeping(UserPrincipal userPrincipal, CreateTimekeepingRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            HtmlUtil.validateRequest(request);
            EmployeeTimesheet employeeTimesheet = new EmployeeTimesheet();
            employeeTimesheet.setStaffNo(request.getStaffNo());
            employeeTimesheet.setTimekeepingDate(request.getTimekeepingDate());
            employeeTimesheet.setClockOut(request.getClockOut());
            employeeTimesheet.setClockIn(request.getClockIn());
            employeeTimesheet.setTimekeepingStatus(Constant.TIMEKEEPING_STATUS.TIMEKEEPING_END);

            long totalWork = request.getClockOut().getTime() - request.getClockIn().getTime();
            employeeTimesheet.setTotalWork(Common.longTimeToHHMM(totalWork));
            employeeTimesheet.setTotalWorkLongTime(totalWork);
            employeeTimesheetRepository.save(employeeTimesheet);

            message = "Create timesheet staff success!";
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
    public ResponseModel checkInEmployee(UserPrincipal userPrincipal, String staffNo) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            HtmlUtil.validateRequest(staffNo);

            EmployeeTimesheet employeeTimesheet = new EmployeeTimesheet();
            employeeTimesheet.setStaffNo(staffNo);
            Date date = new Date(System.currentTimeMillis());
            date.setHours(8);
            date.setMinutes(0);
            date.setSeconds(0);
            long time = date.getTime();
            Timestamp dateCheckIn = new Timestamp(System.currentTimeMillis());
            long checkInTime = dateCheckIn.getTime();
            long lateTime = checkInTime - time;

            employeeTimesheet.setLate(Common.longTimeToHHMM(lateTime));
            employeeTimesheet.setTimekeepingDate(dateCheckIn);
            employeeTimesheet.setClockIn(dateCheckIn);
            employeeTimesheet.setTimekeepingStatus(Constant.TIMEKEEPING_STATUS.TIMEKEEPING_START);
            employeeTimesheet.setGoEarly("00:00");
            employeeTimesheet.setLateTime(lateTime);
            employeeTimesheetRepository.save(employeeTimesheet);

            message = "Check in staff success!";
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
    public ResponseModel checkOutEmployee(UserPrincipal userPrincipal, Integer employeeTimesheetId) {
        try {
            ResponseModel model = new ResponseModel();
            String message;

            EmployeeTimesheet employeeTimesheet = employeeTimesheetRepository
                    .getByEmployeeTimesheetIdAndActiveFlgAndTimekeepingStatus(
                            employeeTimesheetId, Constant.ACTIVE_FLG.NOT_DELETE,
                            Constant.TIMEKEEPING_STATUS.TIMEKEEPING_START)
                    .orElseThrow(() -> new AppException("Check out fails!"));
            Timestamp dateCheckOut = new Timestamp(System.currentTimeMillis());
            long totalWork = dateCheckOut.getTime() - employeeTimesheet.getClockIn().getTime();

            if (employeeTimesheet.getLateTime() < 0) {
                Date date = new Date(System.currentTimeMillis());
                date.setHours(8);
                date.setMinutes(0);
                date.setSeconds(0);
                long time = date.getTime();
                totalWork = dateCheckOut.getTime() - time;
            }
            if (totalWork < 0) totalWork = 0;

            employeeTimesheet.setTotalWork(Common.longTimeToHHMM(totalWork));
            employeeTimesheet.setTotalWorkLongTime(totalWork);
            employeeTimesheet.setClockOut(dateCheckOut);
            employeeTimesheet.setTimekeepingStatus(Constant.TIMEKEEPING_STATUS.TIMEKEEPING_END);
            employeeTimesheet.setUpdatedDate(dateCheckOut);
            employeeTimesheetRepository.save(employeeTimesheet);

            message = "Check out staff success!";
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
    public ResponseModel updateTimekeeping(UserPrincipal userPrincipal, UpdateTimekeepingRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            HtmlUtil.validateRequest(request);

            EmployeeTimesheet employeeTimesheet = employeeTimesheetRepository
                    .getByEmployeeTimesheetIdAndActiveFlgAndTimekeepingStatus(
                            request.getEmployeeTimesheetId(), Constant.ACTIVE_FLG.NOT_DELETE,
                            Constant.TIMEKEEPING_STATUS.TIMEKEEPING_START)
                    .orElseThrow(() -> new AppException("Check out fails!"));

            employeeTimesheet.setTimekeepingDate(request.getTimekeepingDate());
            employeeTimesheet.setClockOut(request.getClockOut());
            employeeTimesheet.setClockIn(request.getClockIn());
            employeeTimesheet.setTimekeepingStatus(Constant.TIMEKEEPING_STATUS.TIMEKEEPING_END);
            long totalWork = request.getClockOut().getTime() - request.getClockIn().getTime();
            employeeTimesheet.setTotalWork(Common.longTimeToHHMM(totalWork));
            employeeTimesheet.setTotalWorkLongTime(totalWork);
            employeeTimesheet.setActiveFlg(request.getActiveFlg());
            employeeTimesheetRepository.save(employeeTimesheet);

            message = "Update timesheet staff success!";
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
    public ResponseModel deleteTimekeeping(UserPrincipal userPrincipal, Integer employeeTimesheetId) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            HtmlUtil.validateRequest(employeeTimesheetId);

            EmployeeTimesheet employeeTimesheet = employeeTimesheetRepository.getByEmployeeTimesheetIdAndActiveFlg(
                            employeeTimesheetId,
                            Constant.ACTIVE_FLG.NOT_DELETE)
                    .orElseThrow(() -> new AppException("Timesheet not found!"));
            employeeTimesheet.setActiveFlg(Constant.ACTIVE_FLG.DELETED);
            employeeTimesheetRepository.save(employeeTimesheet);

            message = "Update timesheet staff success!";
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
    public ResponseModel getOneTimekeeping(UserPrincipal userPrincipal, Timestamp timekeepingDate) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            HtmlUtil.validateRequest(timekeepingDate);

            EmployeeTimesheet employeeTimesheet = employeeTimesheetRepository.getByEmployeeTimesheetDateAndActiveFlgAndStaffNo(
                            timekeepingDate,
                            Constant.ACTIVE_FLG.NOT_DELETE, userPrincipal.getNumberCode())
                    .orElse(null);

            message = "Get one timesheet staff success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(employeeTimesheet);
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
    public ResponseModel checkInOrOutAndroid(UserPrincipal userPrincipal) {
        try {
            EmployeeTimesheet employeeTimesheet = employeeTimesheetRepository.getByEmployeeTimesheetDateAndActiveFlgAndStaffNo(
                            new Timestamp(System.currentTimeMillis()),
                            Constant.ACTIVE_FLG.NOT_DELETE, userPrincipal.getNumberCode())
                    .orElse(null);
            if (employeeTimesheet == null){
                return this.checkInEmployee(userPrincipal, userPrincipal.getNumberCode());
            }else if(Objects.equals(employeeTimesheet.getTimekeepingStatus(), Constant.TIMEKEEPING_STATUS.TIMEKEEPING_START)) {
                return this.checkOutEmployee(userPrincipal,employeeTimesheet.getEmployeeTimesheetId());
            }

            String message = "Check out staff success!";
            ResponseModel model = new ResponseModel();
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
    public ResponseModel getTimekeeping(UserPrincipal userPrincipal, Timestamp timekeepingDate) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
//            HtmlUtil.validateRequest(timekeepingDate);

            List<EmployeeTimesheet> employeeTimesheets = employeeTimesheetRepository.getByTimesheetDateAndActiveFlgAndStaffNo(
                            timekeepingDate,
                            Constant.ACTIVE_FLG.NOT_DELETE, userPrincipal.getNumberCode());

            message = "Get  timesheet staff success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(employeeTimesheets);
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
