package com.truonggiang.employee_management_system.repository.staff;

import com.truonggiang.employee_management_system.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query(value = "select count(st.staff_id) from staff st where st.email like concat(:nameStaff,'%@gmail.com')", nativeQuery = true)
    Integer countEmailStaffFollowName(@Param("nameStaff") String nameStaff);

    @Query(value = "SELECT COUNT(s.staffId) FROM Staff s ")
    Integer getCountStaff();

    Optional<Staff> getByStaffNoAndActiveFlg(String staffNo,Integer activeFlg);
}
