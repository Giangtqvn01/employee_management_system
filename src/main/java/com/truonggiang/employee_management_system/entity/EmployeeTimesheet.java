package com.truonggiang.employee_management_system.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "employee_timesheet")
public class EmployeeTimesheet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employee_timesheet_id")
    private Integer employeeTimesheetId;
    @Basic
    @Column(name = "staff_no", nullable = false)
    private String staffNo;
    @Basic
    @Column(name = "timekeeping_date", nullable = false)
    private Timestamp timekeepingDate;
    @Basic
    @Column(name = "clock_in", nullable = false)
    private Timestamp clockIn;
    @Basic
    @Column(name = "clock_out")
    private Timestamp clockOut;
    @Basic
    @Column(name = "late")
    private String late;
    @Basic
    @Column(name = "go_early")
    private String goEarly;
    @Basic
    @Column(name = "total_work")
    private String totalWork;
    @Basic
    @Column(name = "status", nullable = false, insertable = false)
    private Integer status;
    @Basic
    @Column(name = "active_flg", nullable = false, insertable = false)
    private Integer activeFlg;
    @Basic
    @Column(name = "created_date", nullable = false, insertable = false, updatable = false)
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_date")
    private Timestamp updatedDate;
    @Basic
    @Column(name = "timekeeping_status", nullable = false)
    private Integer timekeepingStatus;
    @Basic
    @Column(name = "total_work_long_time")
    private Long totalWorkLongTime;
    @Basic
    @Column(name = "late_time")
    private Long lateTime;

}
