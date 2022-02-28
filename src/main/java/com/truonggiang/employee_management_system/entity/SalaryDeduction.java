package com.truonggiang.employee_management_system.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "salary_deduction")
/**
 * Khoản khấu trừ
 */
public class SalaryDeduction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "salary_deduction_id")
    private Integer salaryDeductionId;
    @Basic
    @Column(name = "salary_deduction_name")
    private String salaryDeductionName;
    @Basic
    @Column(name = "salary_deduction_money")
    private Long salaryDeductionMoney;
    @Basic
    @Column(name = "active_flg")
    private Integer activeFlg;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_date")
    private Timestamp updatedDate;
    @Basic
    @Column(name = "employee_contract_id")
    private Integer employeeContractId;

}
