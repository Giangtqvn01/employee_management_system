package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
/**
 * chức vụ
 */
public class Position {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "position_id", nullable = false)
    private Integer positionId;
    @Basic
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;
    @Basic
    @Column(name = "position_name", nullable = false)
    private String positionName;
    @Basic
    @Column(name = "position_cd", nullable = false)
    private String positionCd;
    @Basic
    @Column(name = "active_flg", insertable = false)
    private Integer activeFlg;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "status", insertable = false)
    private Integer status;

}
