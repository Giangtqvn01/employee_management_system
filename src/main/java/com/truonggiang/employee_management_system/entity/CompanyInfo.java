package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "company_info")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CompanyInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "company_info_id")
    private Integer companyInfoId;
    @Basic
    @Column(name = "company_name")
    private String companyName;
    @Basic
    @Column(name = "latitude")
    private String latitude;
    @Basic
    @Column(name = "longitude")
    private String longitude;
    @Basic
    @Column(name = "created_date", insertable = false, nullable = false)
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_date")
    private Timestamp updatedDate;

}
