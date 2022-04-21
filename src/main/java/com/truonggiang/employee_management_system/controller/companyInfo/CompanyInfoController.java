package com.truonggiang.employee_management_system.controller.companyInfo;

import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.companyInfo.CreateCompanyInfoRequest;
import com.truonggiang.employee_management_system.model.companyInfo.UpdateCompanyInfoRequest;
import com.truonggiang.employee_management_system.security.CurrentUser;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.service.companyInfo.CompanyInfoService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyInfoController {
    private final Logit log = Logit.getInstance(CompanyInfoController.class);

    @Autowired
    private CompanyInfoService companyInfoService;

    @PostMapping()
    public ResponseEntity<?> createCompanyInfo(@CurrentUser UserPrincipal userPrincipal,
                                               @RequestBody @Valid CreateCompanyInfoRequest request) {
        log.info("Create company info ");
        long start = System.currentTimeMillis();
        ResponseModel model = companyInfoService.createCompanyInfo(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }

    @PostMapping("/update-timekeeping")
    public ResponseEntity<?> updateCompanyInfo(@CurrentUser UserPrincipal userPrincipal,
                                               @RequestBody @Valid UpdateCompanyInfoRequest request) {
        log.info("Create company info ");
        long start = System.currentTimeMillis();
        ResponseModel model = companyInfoService.updateCompanyInfo(userPrincipal, request);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
        return new ResponseEntity(model.getData(), model.getResponseStatus());
    }
}
