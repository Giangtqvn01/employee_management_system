package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "staff_overtime")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StaffOvertime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staff_overtime_id")
    private Integer staffOvertimeId;
    @Basic
    @Column(name = "active_flg", nullable = false, insertable = false)
    private Integer activeFlg;
    @Basic
    @Column(name = "status", nullable = false, insertable = false)
    private Integer status;
    @Basic
    @Column(name = "created_date", nullable = false, insertable = false)
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_date")
    private Timestamp updatedDate;
    @Basic
    @Column(name = "updated_staff_no")
    private String updatedStaffNo;
    @Basic
    @Column(name = "overtime_date", nullable = false)
    private Timestamp overtimeDate;
    @Basic
    @Column(name = "overtime_start", nullable = false)
    private Timestamp overtimeStart;
    @Basic
    @Column(name = "overtime_ends", nullable = false)
    private Timestamp overtimeEnds;
    @Basic
    @Column(name = "description", nullable = false)
    private String description;
    @Basic
    @Column(name = "total_work_long_time", nullable = false)
    private Long totalWorkLongTime;
    @Basic
    @Column(name = "total_work", nullable = false)
    private String totalWork;
    @Basic
    @Column(name = "approval_status", nullable = false, insertable = false)
    private String approvalStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_no", referencedColumnName = "staff_no", nullable = false)
    private Staff staff;
}
