package com.truonggiang.employee_management_system.service.companyInfo;

import com.truonggiang.employee_management_system.entity.CompanyInfo;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.companyInfo.CreateCompanyInfoRequest;
import com.truonggiang.employee_management_system.model.companyInfo.UpdateCompanyInfoRequest;
import com.truonggiang.employee_management_system.repository.companyInfo.CompanyInfoRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Override
    public ResponseModel createCompanyInfo(UserPrincipal userPrincipal, CreateCompanyInfoRequest request) {
        try {
            String message;
            ResponseModel model = new ResponseModel();
            HtmlUtil.validateRequest(request);
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setCompanyName(request.getCompanyName());
            companyInfo.setLatitude(request.getLatitude());
            companyInfo.setLongitude(request.getLongitude());

            companyInfoRepository.save(companyInfo);
            message = "Get one staff information success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(companyInfo);
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
    public ResponseModel updateCompanyInfo(UserPrincipal userPrincipal, UpdateCompanyInfoRequest request) {
        try {
            String message;
            ResponseModel model = new ResponseModel();
            HtmlUtil.validateRequest(request);

            CompanyInfo companyInfo = companyInfoRepository.getByCompanyInfoId(request.getCompanyInfoId()).orElse(null);
            if (companyInfo == null){
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), "Company not found!");
                model.setData(error);
                model.setDescription("Company not found!");
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }

            companyInfo.setCompanyName(request.getCompanyName());
            companyInfo.setLatitude(request.getLatitude());
            companyInfo.setLongitude(request.getLongitude());

            companyInfoRepository.save(companyInfo);
            message = "Get one staff information success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(companyInfo);
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
