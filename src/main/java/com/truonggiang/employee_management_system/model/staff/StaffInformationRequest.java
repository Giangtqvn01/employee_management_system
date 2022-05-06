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

    @NotBlank
    private String staffNo;

    private String storyName;

    private String storyExperience;

    private String facebookUlr;

    private String twitterUrl;

    private String googleUrl;

    private String linkedinUrl;

    private String accountHolderName;

    private String accountNumber;

    private String bankBranch;

    private String bankName;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private String emergencyContactAddress;

    private String emergencyContactEmail;
}
