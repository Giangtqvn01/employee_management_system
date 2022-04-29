package com.truonggiang.employee_management_system.repository.department;

import com.truonggiang.employee_management_system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> getDepartmentByActiveFlgAndDepartmentId(Integer activeFlg, Integer departmentId);

    Optional<Department> getDepartmentByDepartmentId(Integer departmentId);

    @Query("SELECT d FROM Department d" +
            " where d.activeFlg =:activeFlg " +
            "and d.status =:status " +
            "and (:departmentName is null or UPPER(d.departmentName) like concat('%',upper(:departmentName),'%') " +
            "or UPPER(d.departmentCd) like concat('%',upper(:departmentName),'%'))")
    List<Department> getDepartmentByActiveFlgAndStatusAndDepartmentName(@Param("activeFlg") Integer activeFlg,
                                                                        @Param("status") Integer status,
                                                                        @Param("departmentName") String departmentName);

    List<Department> getDepartmentByActiveFlgAndStatus(Integer activeFlg, Integer status);
}
