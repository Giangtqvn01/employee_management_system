package com.truonggiang.employee_management_system.repository.role;

import com.truonggiang.employee_management_system.entity.Role;
import com.truonggiang.employee_management_system.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleCd (RoleName roleName);
}
