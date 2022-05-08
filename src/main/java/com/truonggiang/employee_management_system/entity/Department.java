package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
/**
 * Phòng ban
 */
public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "department_id", insertable = false, updatable = false)
    private Integer departmentId;
    @Basic
    @Column(name = "department_name")
    private String departmentName;
    @Basic
    @Column(name = "department_cd")
    private String departmentCd;
    @Basic
    @Column(name = "active_flg", insertable = false)
    private Integer activeFlg;
    @Basic
    @Column(name = "status", insertable = false)
    private Integer status;
    @Basic
    @Column(name = "description")
    private String description;

}
