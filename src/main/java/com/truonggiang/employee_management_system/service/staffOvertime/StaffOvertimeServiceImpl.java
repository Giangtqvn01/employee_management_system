package com.truonggiang.employee_management_system.service.staffOvertime;

import com.truonggiang.employee_management_system.entity.Staff;
import com.truonggiang.employee_management_system.entity.StaffOvertime;
import com.truonggiang.employee_management_system.entity.User;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.PagedResponeMapper;
import com.truonggiang.employee_management_system.model.PagedResponse;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staffOvertime.AddStaffOvertimeRequest;
import com.truonggiang.employee_management_system.model.staffOvertime.UpdateStaffOvertimeRequest;
import com.truonggiang.employee_management_system.repository.UserRepository;
import com.truonggiang.employee_management_system.repository.staff.StaffRepository;
import com.truonggiang.employee_management_system.repository.staffOvertime.StaffOvertimeRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.Common;
import com.truonggiang.employee_management_system.utils.Constant;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;

@Service
public class StaffOvertimeServiceImpl implements StaffOvertimeService {
    @Autowired
    private StaffOvertimeRepository staffOvertimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Override
    @Transactional
    public ResponseModel addStaffOvertime(UserPrincipal userPrincipal, AddStaffOvertimeRequest request) {
        HtmlUtil.validateRequest(request);
        String message;
        ResponseModel model = new ResponseModel();
        try {
            Staff staff = staffRepository.getByStaffNoAndActiveFlg(request.getStaffNo(), Constant.ACTIVE_FLG.NOT_DELETE)
                    .orElse(null);
            if (staff == null ){
                message = "Staff not found";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            StaffOvertime staffOvertime = new StaffOvertime();
            staffOvertime.setStaff(staff);
            staffOvertime.setDescription(request.getDescription());
            staffOvertime.setOvertimeDate(request.getOvertimeDate());
            staffOvertime.setOvertimeStart(request.getOvertimeStart());
            staffOvertime.setOvertimeEnds(request.getOvertimeEnds());
            staffOvertime.setOvertimeDate(request.getOvertimeDate());

            long totalWork = request.getOvertimeEnds().getTime() - request.getOvertimeStart().getTime();
            staffOvertime.setTotalWork(Common.longTimeToHHMM(totalWork));
            staffOvertime.setTotalWorkLongTime(totalWork);
            staffOvertimeRepository.save(staffOvertime);

            message = "Create  staff overtime success!";
            model.setData(staffOvertime);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error!");
            model.setData(error);
            model.setDescription("Server error!");
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    @Transactional
    public ResponseModel updateStaffOvertime(UserPrincipal userPrincipal, UpdateStaffOvertimeRequest request) {
        HtmlUtil.validateRequest(request);
        String message;
        ResponseModel model = new ResponseModel();
        try {
            StaffOvertime staffOvertime =
                    staffOvertimeRepository.getStaffOvertimesByStaffOvertimeIdAndActiveFlg(request.getStaffOvertimeId(),
                            Constant.ACTIVE_FLG.NOT_DELETE).orElse(null);
            if (staffOvertime == null){
                message = "Staff overtime not found";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            User user = userRepository.findByUserId(userPrincipal.getUserId()).orElse(null);
            if (user == null){
                message = "Get user login not found";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }

            staffOvertime.setStatus(request.getStatus());
            staffOvertime.setActiveFlg(request.getActiveFlg());
            staffOvertime.setDescription(request.getDescription());
            staffOvertime.setOvertimeDate(request.getOvertimeDate());
            staffOvertime.setOvertimeStart(request.getOvertimeStart());
            staffOvertime.setOvertimeEnds(request.getOvertimeEnds());
            staffOvertime.setOvertimeDate(request.getOvertimeDate());
            staffOvertime.setUpdatedStaffNo(user.getNumberCode());
            staffOvertime.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

            long totalWork = request.getOvertimeEnds().getTime() - request.getOvertimeStart().getTime();
            staffOvertime.setTotalWork(Common.longTimeToHHMM(totalWork));
            staffOvertime.setTotalWorkLongTime(totalWork);
            staffOvertimeRepository.save(staffOvertime);
            message = "Get one staff information success!";
            model.setData(staffOvertime);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error!");
            model.setData(error);
            model.setDescription("Server error!");
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    public ResponseModel getStaffOvertime(UserPrincipal userPrincipal, String staffRequest, Integer page,
                                          Integer size) {

        ResponseModel model = new ResponseModel();
        String message = "";
        try {
            if(StringUtils.isEmpty(staffRequest)) staffRequest="";
            HtmlUtil.validateRequest(staffRequest);
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<StaffOvertime> drgNotifications = staffOvertimeRepository.getStaffOvertime(staffRequest, pageable);
            PagedResponse pagedResponse = PagedResponeMapper.mapper(drgNotifications);

            message = "Get all notification in store success!";
            model.setData(pagedResponse);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);

            return model;

        } catch (RuntimeException e) {
            message = "Server error!";
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setData(error);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }
}
