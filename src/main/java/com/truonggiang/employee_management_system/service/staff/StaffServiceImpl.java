package com.truonggiang.employee_management_system.service.staff;

import com.truonggiang.employee_management_system.entity.*;
import com.truonggiang.employee_management_system.exception.AppException;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.PagedResponeMapper;
import com.truonggiang.employee_management_system.model.PagedResponse;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.AddStaffRequest;
import com.truonggiang.employee_management_system.model.staff.UpdateStaffRequest;
import com.truonggiang.employee_management_system.model.staff.UpdateUrlAvatarStaffRequest;
import com.truonggiang.employee_management_system.repository.UserRepository;
import com.truonggiang.employee_management_system.repository.role.RoleRepository;
import com.truonggiang.employee_management_system.repository.staff.StaffRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.Constant;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public ResponseModel addStaff(UserPrincipal userPrincipal, AddStaffRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            request = (AddStaffRequest) HtmlUtil.validateRequest(request);

            Staff staff = new Staff();
            staff.setFirstName(request.getFirstName());
            staff.setLastName(request.getLastName());
            staff.setPhoneNo(request.getPhoneNo());
            staff.setGender(request.getGender());
            String nameStaff = standardizedStrings(request.getLastName(), request.getFirstName());
            Integer countEmailStaff = staffRepository.countEmailStaffFollowName(nameStaff);
            staff.setEmail(nameStaff + (countEmailStaff + 1) + "@gmail.com");
            Integer countStaff = staffRepository.getCountStaff() + 1;
            String staffCode = "00000000".substring(0, 8 - (countStaff + "").length()) + countStaff;
            staff.setStaffNo(staffCode);
            staff.setUrlAvatar(request.getUrlAvatar());

            User user = new User();
            user.setUserName(staff.getEmail());
            user.setFullName(staff.getLastName() + " " + staff.getFirstName());
            user.setPassword(passwordEncoder.encode(Constant.PASSWORD.DEFAULT));
            user.setNumberCode(staffCode);
            Role userRole = roleRepository.findByRoleCd(RoleName.STAFF)
                    .orElseThrow(() -> new AppException("User Role not set."));
            user.setRoles(Collections.singleton(userRole));
            userRepository.save(user);

            EmployeeContract employeeContract = new EmployeeContract();
            employeeContract.setEmployeeSalary(request.getSalaryStaff());
            employeeContract.setDepartmentId(request.getDepartmentId());
            employeeContract.setPositionId(request.getPositionId());
            employeeContract.setContractDate(request.getContractDate());

            staff.addEmployeeContract(employeeContract);

            staffRepository.save(staff);

            message = "Created staff success!";
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
    public ResponseModel updateStaff(UserPrincipal userPrincipal, UpdateStaffRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            request = (UpdateStaffRequest) HtmlUtil.validateRequest(request);
            Staff staff = staffRepository.getByStaffNoAndActiveFlg(request.getStaffNo(), Constant.ACTIVE_FLG.NOT_DELETE)
                    .orElse(null);
            if (staff == null) {
                message = "Staff not found";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }

            staff.setPhoneNo(request.getPhoneNo());
            staff.setGender(request.getGender());
            staff.setMaritalStatus(request.getMaritalStatus());
            staff.setDateOfBirth(request.getDateOfBirth());
            staff.setCity(request.getCity());
            staff.setProvince(request.getProvince());
            staff.setPostalCode(request.getPostalCode());
            staff.setReligion(request.getReligion());
            staff.setBloodGroup(request.getBloodGroup());
            staff.setNationality(request.getNationality());
            staff.setIdentityCardNumber(request.getIdentityCardNumber());
            staff.setEthnic(request.getEthnic());
            staff.setPlaceOfPermanent1(request.getPlaceOfPermanent1());
            staff.setPlaceOfPermanent2(request.getPlaceOfPermanent2());
            staff.setUpdatedDate(Timestamp.from(Instant.now()));
            staff.setStatus(request.getStatus());
            staff.setActiveFlg(request.getActiveFlg());
            staff.setUrlAvatar(request.getUrlAvatar());
            staffRepository.save(staff);


            message = "Update staff success!";
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
    public ResponseModel getOneStaff(UserPrincipal userPrincipal, String staffNo) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            staffNo = (String) HtmlUtil.validateRequest(staffNo);
            Staff staff = staffRepository.getByStaffNoAndActiveFlg(staffNo, Constant.ACTIVE_FLG.NOT_DELETE)
                    .orElse(null);
            if (staff == null) {
                message = "Staff not found";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            message = "Get staff success!";
//            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(staff);
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
    public ResponseModel updateUrlAvatarStaff(UserPrincipal userPrincipal, UpdateUrlAvatarStaffRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message;
            request = (UpdateUrlAvatarStaffRequest) HtmlUtil.validateRequest(request);
            Staff staff = staffRepository.getByStaffNoAndActiveFlg(request.getStaffNo(), Constant.ACTIVE_FLG.NOT_DELETE)
                    .orElse(null);
            if (staff == null) {
                message = "Staff not found";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            staff.setUrlAvatar(request.getUrlImage());
            staff.setUpdatedDate(Timestamp.from(Instant.now()));
            staffRepository.save(staff);

            message = "Update url avatar staff success!";
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
    public ResponseModel getStaff(UserPrincipal userPrincipal, String staffRequest, Integer page, Integer size) {
        ResponseModel model = new ResponseModel();
        String message = "";
        try {
            if(StringUtils.isEmpty(staffRequest)) staffRequest="";
            HtmlUtil.validateRequest(staffRequest);
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<Staff> drgNotifications = staffRepository.getStaff(staffRequest, pageable);
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

    private String standardizedStrings(String lastName, String fistName) {
        lastName = lastName.trim().toLowerCase();
        lastName = lastName.replaceAll("\\s+", " ");
        String[] temp = lastName.split(" ");
        StringBuilder fistNameBuilder = new StringBuilder(fistName);
        for (int i = 0; i < temp.length; i++) {
            fistNameBuilder.append(String.valueOf(temp[i].charAt(0)).toUpperCase());
        }
        fistName = fistNameBuilder.toString();
        return fistName.toLowerCase();
    }
}
