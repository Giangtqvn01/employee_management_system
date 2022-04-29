package com.truonggiang.employee_management_system.repository.position;

import com.truonggiang.employee_management_system.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query("SELECT p FROM Position p WHERE p.activeFlg =:activeFlg and p.status=:status and " +
            "(:positionName is null or UPPER(p.positionName) like concat('%',:positionName,'%') or" +
            " UPPER(p.positionCd) like concat('%',:positionName,'%') )" +
            " and (:departmentId is null or p.departmentId=:departmentId)")
    List<Position> getByActiveFlgAndStatusAndPositionNameAndDepartmentId(@Param("activeFlg") Integer activeFlg,
                                                                         @Param("status") Integer status,
                                                                         @Param("positionName") String positionName,
                                                                         @Param("departmentId") Integer departmentId);
}
