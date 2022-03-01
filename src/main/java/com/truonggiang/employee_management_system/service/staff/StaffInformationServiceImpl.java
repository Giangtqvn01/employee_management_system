package com.truonggiang.employee_management_system.service.staff;

import com.truonggiang.employee_management_system.entity.StaffInformation;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.staff.StaffInformationRequest;
import com.truonggiang.employee_management_system.repository.staff.StaffInformationRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StaffInformationServiceImpl implements StaffInformationService {
    @Autowired
    private StaffInformationRepository staffInformationRepository;

    @Override
    public ResponseModel createOrUpdateStaffInformation(UserPrincipal userPrincipal, StaffInformationRequest request) {
        try {
            HtmlUtil.validateRequest(request);
            StaffInformation staffInformation = staffInformationRepository.getByStaffNo(request.getStaffNo())
                    .orElse(null);
            if (staffInformation == null) {
                return this.createStaffInformation(request);
            } else {
                return this.updateStaffInformation(request, staffInformation);
            }
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
    public ResponseModel getOneStaffInformation(UserPrincipal userPrincipal, String staffNo) {
        try {
            ResponseModel model = new ResponseModel();
            String message = "";
            HtmlUtil.validateRequest(staffNo);
            StaffInformation staffInformation = staffInformationRepository.getByStaffNo(staffNo)
                    .orElse(null);
            if (staffInformation == null) {
                staffInformation = new StaffInformation();
            }
            message = "Get one staff information success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(staffInformation);
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

    @Transactional
    public ResponseModel createStaffInformation(StaffInformationRequest request) {
        try {
            ResponseModel model = new ResponseModel();
            String message = "";

            StaffInformation staffInformation = new StaffInformation();
            staffInformation.setStaffNo(request.getStaffNo());
            staffInformation.setStoryName(request.getStoryName());
            staffInformation.setStoryExperience(request.getStoryExperience());
            staffInformation.setFacebookUlr(request.getFacebookUlr());
            staffInformation.setTwitterUrl(request.getTwitterUrl());
            staffInformation.setGoogleUrl(request.getGoogleUrl());
            staffInformation.setLinkedinUrl(request.getLinkedinUrl());
            staffInformation.setAccountHolderName(request.getAccountHolderName());
            staffInformation.setAccountNumber(request.getAccountNumber());
            staffInformation.setBankBranch(request.getBankBranch());
            staffInformation.setEmergencyContactName(request.getEmergencyContactName());
            staffInformation.setEmergencyContactPhone(request.getEmergencyContactPhone());
            staffInformation.setEmergencyContactAddress(request.getEmergencyContactAddress());
            staffInformation.setEmergencyContactEmail(request.getEmergencyContactEmail());
            staffInformation.setBankName(request.getBankName());
            staffInformationRepository.save(staffInformation);

            message = "Create staff information success!";
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

    @Transactional
    public ResponseModel updateStaffInformation(StaffInformationRequest request, StaffInformation staffInformation) {
        try {
            ResponseModel model = new ResponseModel();
            String message = "";

            if (staffInformation.getStaffInformationId().intValue() != request.getStaffInformationId().intValue()) {
                message = "Staff not found";
                BaseModel success = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setData(success);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }

            staffInformation.setStoryName(request.getStoryName());
            staffInformation.setStoryExperience(request.getStoryExperience());
            staffInformation.setFacebookUlr(request.getFacebookUlr());
            staffInformation.setTwitterUrl(request.getTwitterUrl());
            staffInformation.setGoogleUrl(request.getGoogleUrl());
            staffInformation.setLinkedinUrl(request.getLinkedinUrl());
            staffInformation.setAccountHolderName(request.getAccountHolderName());
            staffInformation.setAccountNumber(request.getAccountNumber());
            staffInformation.setBankBranch(request.getBankBranch());
            staffInformation.setEmergencyContactName(request.getEmergencyContactName());
            staffInformation.setEmergencyContactPhone(request.getEmergencyContactPhone());
            staffInformation.setEmergencyContactAddress(request.getEmergencyContactAddress());
            staffInformation.setEmergencyContactEmail(request.getEmergencyContactEmail());
            staffInformationRepository.save(staffInformation);

            message = "Update staff information success!";
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
}
