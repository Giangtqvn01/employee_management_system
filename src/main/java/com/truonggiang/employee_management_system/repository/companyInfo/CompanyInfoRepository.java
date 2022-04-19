package com.truonggiang.employee_management_system.repository.companyInfo;

import com.truonggiang.employee_management_system.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Integer> {
    Optional<CompanyInfo> getByCompanyInfoId(Integer companyInfoId);
}
