package com.truonggiang.employee_management_system.repository.staff;

import com.truonggiang.employee_management_system.entity.StaffInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffInformationRepository extends JpaRepository<StaffInformation, Integer> {
    Optional<StaffInformation> getByStaffNo(String staffNo);
}
