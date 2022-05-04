package com.truonggiang.employee_management_system.repository.staffOvertime;

import com.truonggiang.employee_management_system.entity.StaffOvertime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffOvertimeRepository extends JpaRepository<StaffOvertime, Integer> {
    Optional<StaffOvertime> getStaffOvertimesByStaffOvertimeIdAndActiveFlg(Integer staffOvertime, Integer activeFlg);

    Page<StaffOvertime> getStaffOvertimesByStaff(String staffRequest, Pageable pageable);


    @Query(value = "select so.* from staff_overtime so\n" +
            "join staff s on (so.staff_no =s.staff_no and s.active_flg =1)\n" +
            "where  s.staff_no like concat('%',:staffRequest,'%') \n" +
            "or concat(s.last_name, ' ', s.first_name) like concat('%',:staffRequest,'%') \n" +
            "or s.email like concat('%',:staffRequest,'%') ",
            countQuery = "select count(*) from (" +
                    " select so.* from staff_overtime so\n" +
                    "join staff s on (so.staff_no =s.staff_no and s.active_flg =1)\n" +
                    "where  s.staff_no like concat('%',:staffRequest,'%') \n" +
                    "or concat(s.last_name, ' ', s.first_name) like concat('%',:staffRequest,'%') \n" +
                    "or s.email like concat('%',:staffRequest,'%') " +
                    ")",
            nativeQuery = true)
    Page<StaffOvertime> getStaffOvertime(String staffRequest, Pageable pageable);
}
