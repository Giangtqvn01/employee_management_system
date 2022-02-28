package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_cd")
    private RoleName roleCd;
    @Basic
    @Column(name = "role_name")
    private String roleName;
    @Basic
    @Column(name = "description")
    private String description;

}
