package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "fringe_benefits")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

/**
 * Phụ cấp
 */
public class FringeBenefits {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fringe_benefits_id")
    private Integer fringeBenefitsId;
    @Basic
    @Column(name = "employee_contract_id")
    private Integer employeeContractId;
    @Basic
    @Column(name = "fringe_benefits_name")
    private String fringeBenefitsName;
    @Basic
    @Column(name = "subsidy")
    private Integer subsidy;
    @Basic
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_date")
    private Timestamp updatedDate;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "active_flg")
    private Integer activeFlg;

}
