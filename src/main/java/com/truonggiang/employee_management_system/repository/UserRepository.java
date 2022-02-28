package com.truonggiang.employee_management_system.repository;

import com.truonggiang.employee_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(int id);

    Optional<User> findByNumberCodeAndActiveFlg(String loginId, Integer activeFlg);

    Optional<User> findByUserNameAndActiveFlg(String loginId, Integer activeFlg);
}
