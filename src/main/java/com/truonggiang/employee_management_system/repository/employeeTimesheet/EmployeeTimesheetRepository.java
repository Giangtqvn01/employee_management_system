package com.truonggiang.employee_management_system.repository.employeeTimesheet;

import com.truonggiang.employee_management_system.entity.EmployeeTimesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeTimesheetRepository extends JpaRepository<EmployeeTimesheet, Integer> {

    Optional<EmployeeTimesheet> getByEmployeeTimesheetIdAndActiveFlgAndTimekeepingStatus(
            Integer employeeTimesheetId, Integer activeFlg, Integer timekeepingStatus);

    Optional<EmployeeTimesheet> getByEmployeeTimesheetIdAndActiveFlg(Integer employeeTimesheetId, Integer activeFlg);
}
