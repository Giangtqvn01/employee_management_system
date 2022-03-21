package com.truonggiang.employee_management_system.repository.employeeTimesheet;

import com.truonggiang.employee_management_system.entity.EmployeeTimesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface EmployeeTimesheetRepository extends JpaRepository<EmployeeTimesheet, Integer> {

    Optional<EmployeeTimesheet> getByEmployeeTimesheetIdAndActiveFlgAndTimekeepingStatus(
            Integer employeeTimesheetId, Integer activeFlg, Integer timekeepingStatus);

    Optional<EmployeeTimesheet> getByEmployeeTimesheetIdAndActiveFlg(Integer employeeTimesheetId, Integer activeFlg);

    @Query(value = "select * from employee_timesheet " +
            " where date_format(timekeeping_date, '%Y-%m-%d') = date_format(:timesheetDate, '%Y-%m-%d')" +
            " and staff_no =:staffNo  and active_flg =:activeFlg ", nativeQuery = true)
    Optional<EmployeeTimesheet> getByEmployeeTimesheetDateAndActiveFlgAndStaffNo(
            @Param("timesheetDate") Timestamp timesheetDate,
            @Param("activeFlg") Integer activeFlg, @Param("staffNo") String staffNo);
}
