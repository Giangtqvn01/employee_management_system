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

    private String staffNo;
    @NotBlank

    private String phoneNo;
    private Integer gender;
    private Timestamp dateOfBirth;

    private String maritalStatus;

    private String province;

    private String city;

    private String postalCode;

    private String religion;

    private String bloodGroup;

    private String nationality;

    private String identityCardNumber;

    private String ethnic;

    private String placeOfPermanent1;

    private String placeOfPermanent2;

    private String urlAvatar;
    private int status;
    private int activeFlg;
    private Integer staffManagerId;

}
