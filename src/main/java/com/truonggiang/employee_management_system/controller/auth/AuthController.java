package com.truonggiang.employee_management_system.controller.auth;

import com.truonggiang.employee_management_system.entity.User;
import com.truonggiang.employee_management_system.model.ResponseModel;
import com.truonggiang.employee_management_system.model.auth.JwtAuthenticationResponse;
import com.truonggiang.employee_management_system.model.auth.LoginRequest;
import com.truonggiang.employee_management_system.model.auth.RegisterRequest;
import com.truonggiang.employee_management_system.security.JwtTokenProvider;
import com.truonggiang.employee_management_system.service.auth.AuthService;
import com.truonggiang.employee_management_system.service.user.UserService;
import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static Logit log = Logit.getInstance(AuthController.class);
    @Autowired
    private AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
//        log.info("Register name =" + request.getFullName());
//        long start = System.currentTimeMillis();
//        ResponseModel model = authService.registerUser(request);
//        long end = System.currentTimeMillis();
//        long diff = end - start;
//        log.info("Code = " + model.getResponseStatus() + "," + model.getDescription() + ",time = " + diff);
//        return new ResponseEntity(model.getData(), model.getResponseStatus());
//    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        log.info("Login account  =" + request.getUserName());
        long start = System.currentTimeMillis();
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            User account = authService.getAccountInfoByLoginId(request.getUserName());
            if (account == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String jwt = tokenProvider.generateToken(authenticate);
            long end = System.currentTimeMillis();
            long diff = end - start;
            log.info("Token : " + jwt + ",time = " + diff);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, account));
        } catch (Exception e) {
            log.info("User name or password fails! ");
            log.info(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
