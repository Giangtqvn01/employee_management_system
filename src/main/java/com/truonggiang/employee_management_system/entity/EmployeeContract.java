package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "employee_contract")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
/**
 * Hợp đồng nhân viên
 */
public class EmployeeContract {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employee_contract_id")
    private Integer employeeContractId;
    @Basic
    @Column(name = "staff_id")
    private Integer staffId;
    @Basic
    @Column(name = "status", insertable = false, nullable = false)
    private Integer status;
    @Basic
    @Column(name = "active_flg", insertable = false, nullable = false)
    private Integer activeFlg;
    @Basic
    @Column(name = "contract_date")
    private Timestamp contractDate;
    @Basic
    @Column(name = "employee_salary")
    private String employeeSalary;
    @Basic
    @Column(name = "contract_end_date")
    private Timestamp contractEndDate;
    @Basic
    @Column(name = "created_date", insertable = false, updatable = false, nullable = false)
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_date", insertable = false)
    private Timestamp updatedDate;
    @Basic
    @Column(name = "department_id")
    private Integer departmentId;
    @Basic
    @Column(name = "position_id")
    private Integer positionId;

}
