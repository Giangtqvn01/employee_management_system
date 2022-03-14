package com.truonggiang.employee_management_system.utils;

public interface Constant {
    interface ACTIVE_FLG {
        Integer DELETED = 0;
        Integer NOT_DELETE = 1;
    }

    interface ROLE {
        String admin = "ADMIN";
        String staff = "STAFF";
    }

    interface LOGIN {
        Integer FIRST = 1;
        Integer MANY_TIMES = 0;
    }

    interface PASSWORD {
        String DEFAULT = "123456";
    }

    interface TIMEKEEPING_STATUS {
        Integer TIMEKEEPING_START = 1;
        Integer TIMEKEEPING_END = 0;
    }
}
