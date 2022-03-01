package com.truonggiang.employee_management_system.security;

import com.truonggiang.employee_management_system.entity.User;
import com.truonggiang.employee_management_system.repository.UserRepository;
import com.truonggiang.employee_management_system.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId)
            throws UsernameNotFoundException {
        User user = userRepository.findByUserNameAndActiveFlg(loginId, Constant.ACTIVE_FLG.NOT_DELETE)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + loginId)
                );
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userRepository.findByUserId(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return UserPrincipal.create(user);
    }
}
