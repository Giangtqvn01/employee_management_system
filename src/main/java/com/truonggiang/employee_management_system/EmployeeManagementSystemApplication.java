package com.truonggiang.employee_management_system;

import com.truonggiang.employee_management_system.utils.Logit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemApplication {
	private static Logit log = Logit.getInstance(EmployeeManagementSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

}
