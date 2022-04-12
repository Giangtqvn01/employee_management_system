package com.truonggiang.employee_management_system.repository.staffOvertime;

import com.truonggiang.employee_management_system.utils.Logit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class StaffOvertimeCustomerImpl implements StaffOvertimeCustomer {
    private final Logit log = Logit.getInstance(StaffOvertimeCustomerImpl.class);
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;
}
