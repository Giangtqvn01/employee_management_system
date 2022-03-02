package com.truonggiang.employee_management_system.model.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StaffInformationRequest {
    private Integer staffInformationId;
    @SQLInjectionSafe
    @NotBlank
    private String staffNo;
    @SQLInjectionSafe
    private String storyName;
    @SQLInjectionSafe
    private String storyExperience;
    @SQLInjectionSafe
    private String facebookUlr;
    @SQLInjectionSafe
    private String twitterUrl;
    @SQLInjectionSafe
    private String googleUrl;
    @SQLInjectionSafe
    private String linkedinUrl;
    @SQLInjectionSafe
    private String accountHolderName;
    @SQLInjectionSafe
    private String accountNumber;
    @SQLInjectionSafe
    private String bankBranch;
    @SQLInjectionSafe
    private String bankName;
    @SQLInjectionSafe
    private String emergencyContactName;
    @SQLInjectionSafe
    private String emergencyContactPhone;
    @SQLInjectionSafe
    private String emergencyContactAddress;
    @SQLInjectionSafe
    private String emergencyContactEmail;
}
