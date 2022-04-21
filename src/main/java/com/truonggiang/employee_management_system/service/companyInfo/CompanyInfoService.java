package com.truonggiang.employee_management_system.service.companyInfo;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.companyInfo.CreateCompanyInfoRequest;
import com.truonggiang.employee_management_system.model.companyInfo.UpdateCompanyInfoRequest;
import com.truonggiang.employee_management_system.security.UserPrincipal;

public interface CompanyInfoService {
    ResponseModel createCompanyInfo(UserPrincipal userPrincipal, CreateCompanyInfoRequest request);

    ResponseModel updateCompanyInfo(UserPrincipal userPrincipal, UpdateCompanyInfoRequest request);
}
