package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false, updatable = false, insertable = false)
    private Integer userId;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Basic
    @Column(name = "status", insertable = false)
    private Integer status;
    @Basic
    @Column(name = "active_flg", insertable = false)
    private Integer activeFlg;
    @Basic
    @Column(name = "number_code")
    private String numberCode;
    @Basic
    @Column(name = "login_fist_time", insertable = false, nullable = false)
    private Integer loginFistTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

}
