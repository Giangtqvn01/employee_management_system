package com.truonggiang.employee_management_system.service.department;

import com.truonggiang.employee_management_system.entity.CompanyInfo;
import com.truonggiang.employee_management_system.entity.Department;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.department.CreateDepartmentRequest;
import com.truonggiang.employee_management_system.model.department.UpdateDepartmentRequest;
import com.truonggiang.employee_management_system.repository.department.DepartmentRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseModel createDepartment(UserPrincipal userPrincipal, CreateDepartmentRequest request) {
        try {
            String message;
            ResponseModel model = new ResponseModel();
            HtmlUtil.validateRequest(request);
            Department department = new Department();
            department.setDepartmentCd(request.getDepartmentCd());
            department.setDepartmentName(request.getDepartmentName());
            department.setDescription(request.getDescription());
            departmentRepository.save(department);
            message = "Create department success!";
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
    public ResponseModel updateDepartment(UserPrincipal userPrincipal, UpdateDepartmentRequest request) {
        try {
            String message;
            ResponseModel model = new ResponseModel();
            HtmlUtil.validateRequest(request);
            Department department =
                    departmentRepository.getDepartmentByDepartmentId(request.getDepartmentId()).orElse(null);
            if (department==null){
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), "Department not found!");
                model.setData(error);
                model.setDescription("Department not found!");
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            department.setActiveFlg(request.getActiveFlg());
            department.setStatus(request.getStatus());
            department.setDepartmentName(request.getDepartmentName());
            department.setDescription(request.getDescription());
            departmentRepository.save(department);
            message = "Update department success!";
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
    public ResponseModel getDepartment(UserPrincipal userPrincipal, Integer status, Integer activeFlg,
                                       String departmentName) {
        try {
            String message;
            ResponseModel model = new ResponseModel();

            List<Department> departments =
                    departmentRepository.getDepartmentByActiveFlgAndStatusAndDepartmentName(activeFlg, status,
                            departmentName);
            message = "Get list departments success!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(departments);
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
