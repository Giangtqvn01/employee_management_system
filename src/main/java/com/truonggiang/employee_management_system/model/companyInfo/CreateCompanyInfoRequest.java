package com.truonggiang.employee_management_system.model.companyInfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateCompanyInfoRequest {
    private String companyName;
    private String latitude;
    private String longitude;
}
