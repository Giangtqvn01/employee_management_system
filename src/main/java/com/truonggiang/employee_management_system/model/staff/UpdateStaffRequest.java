package com.truonggiang.employee_management_system.model.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateStaffRequest {
    @NotBlank
    @SQLInjectionSafe
    private String staffNo;
    @NotBlank
    @SQLInjectionSafe
    private String phoneNo;
    private Integer gender;
    private Timestamp dateOfBirth;
    @SQLInjectionSafe
    private String maritalStatus;
    @SQLInjectionSafe
    private String province;
    @SQLInjectionSafe
    private String city;
    @SQLInjectionSafe
    private String postalCode;
    @SQLInjectionSafe
    private String religion;
    @SQLInjectionSafe
    private String bloodGroup;
    @SQLInjectionSafe
    private String nationality;
    @SQLInjectionSafe
    private String identityCardNumber;
    @SQLInjectionSafe
    private String ethnic;
    @SQLInjectionSafe
    private String placeOfPermanent1;
    @SQLInjectionSafe
    private String placeOfPermanent2;
    @SQLInjectionSafe
    private String urlAvatar;
    private int status;
    private int activeFlg;
    private Integer staffManagerId;

}
