package com.truonggiang.employee_management_system.model.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.truonggiang.employee_management_system.entity.User;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User account;

    public JwtAuthenticationResponse(String accessToken, User account) {
        this.accessToken = accessToken;
        this.account = account;

    }
}
