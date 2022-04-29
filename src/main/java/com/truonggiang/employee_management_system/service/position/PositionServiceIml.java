package com.truonggiang.employee_management_system.service.position;

import com.truonggiang.employee_management_system.entity.Position;
import com.truonggiang.employee_management_system.model.BaseModel;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.position.CreatePositionRequest;
import com.truonggiang.employee_management_system.model.position.UpdatePositionRequest;
import com.truonggiang.employee_management_system.repository.position.PositionRepository;
import com.truonggiang.employee_management_system.security.UserPrincipal;
import com.truonggiang.employee_management_system.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceIml implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public ResponseModel createPosition(UserPrincipal userPrincipal, CreatePositionRequest request) {
        try {
            String message;
            ResponseModel model = new ResponseModel();
            HtmlUtil.validateRequest(request);
            Position position = new Position();
            position.setPositionCd(request.getPositionCd());
            position.setPositionName(request.getPositionName());
            position.setDescription(request.getDescription());
            position.setDepartmentId(request.getDepartmentId());
            message = "Create position success!";
            positionRepository.save(position);
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
    public ResponseModel updatePosition(UserPrincipal userPrincipal, UpdatePositionRequest request) {
        try {
            String message;
            ResponseModel model = new ResponseModel();
            HtmlUtil.validateRequest(request);

            Position position = positionRepository.findById(request.getPositionId()).orElse(null);
            if (position == null) {
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), "Position not found!");
                model.setData(error);
                model.setDescription("Position not found!");
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            position.setActiveFlg(request.getActiveFlg());
            position.setStatus(request.getStatus());
            position.setPositionName(request.getPositionName());
            position.setDescription(request.getDescription());
            position.setDepartmentId(request.getDepartmentId());
            message = "Update position success!";
            positionRepository.save(position);
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
    public ResponseModel getPosition(UserPrincipal userPrincipal, Integer status,
                                     Integer activeFlg, Integer departmentId, String positionName) {
        try {
            String message;
            ResponseModel model = new ResponseModel();
            List<Position> positions = positionRepository.getByActiveFlgAndStatusAndPositionNameAndDepartmentId(
                    activeFlg, status, positionName, departmentId);
            message = "Get position success! ";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(positions);
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
