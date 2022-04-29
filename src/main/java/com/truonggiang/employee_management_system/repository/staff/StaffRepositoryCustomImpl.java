package com.truonggiang.employee_management_system.repository.staff;

import com.truonggiang.employee_management_system.model.staff.GetStaffByDepartmentRequest;
import com.truonggiang.employee_management_system.model.staff.StaffByDepartment;
import com.truonggiang.employee_management_system.utils.DataUtil;
import com.truonggiang.employee_management_system.utils.Logit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaffRepositoryCustomImpl implements StaffRepositoryCustom{
    private final Logit log = Logit.getInstance(StaffRepositoryCustomImpl.class);
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public List<StaffByDepartment> getStaffByDepartment(GetStaffByDepartmentRequest request) {
        List<StaffByDepartment> responses = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            HashMap map = new HashMap();
            strQueryGetStaffByDepartment(map, stringBuilder, request);
            Query query = entityManager.createNativeQuery(stringBuilder.toString());
            map.forEach((k, v) -> query.setParameter(k.toString(), v));
            List<Object[]> resultList = query.getResultList();
            if (resultList != null) {
                resultList.forEach(object -> {
                    StaffByDepartment response = new StaffByDepartment();
                    response.setDepartmentName(DataUtil.safeToString(object[0]));
                    response.setDepartmentCd(DataUtil.safeToString(object[1]));
                    response.setStaffId(DataUtil.safeToInt(object[2]));
                    response.setStaffNo(DataUtil.safeToString(object[3]));
                    response.setLastName(DataUtil.safeToString(object[4]));
                    response.setFirstName(DataUtil.safeToString(object[5]));
                    response.setPhoneNo(DataUtil.safeToString(object[6]));
                    response.setGender(DataUtil.safeToString(object[7]));
                    response.setPositionName(DataUtil.safeToString(object[8]));
                    response.setSmLastName(DataUtil.safeToString(object[9]));
                    response.setFirstName(DataUtil.safeToString(object[10]));
                    response.setSActiveFlg(DataUtil.safeToInt(object[11]));
                    response.setDActiveFlg(DataUtil.safeToInt(object[12]));
                    response.setPActiveFlg(DataUtil.safeToInt(object[13]));
                    responses.add(response);
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responses;
    }

    private void strQueryGetStaffByDepartment(HashMap map, StringBuilder stringBuilder, GetStaffByDepartmentRequest request) {
        stringBuilder.append(" select \n" +
                "\td.department_name,\n" +
                "    d.department_cd,\n" +
                "    s.staff_id,\n" +
                "    s.staff_no,\n" +
                "    s.last_name,\n" +
                "    s.first_name,\n" +
                "    s.phone_no,\n" +
                "    s.gender,\n" +
                "    p.position_name,\n" +
                "    sm.last_name sm_last_name,\n" +
                "    sm.first_name sm_first_name,\n" +
                "    s.active_flg s_active_flg,\n" +
                "    d.active_flg d_active_flg,\n" +
                "    p.active_flg p_active_flg\n" +
                " from department d \n" +
                "left join employee_contract ec on (ec.department_id = d.department_id)\n" +
                "left join staff s on(s.staff_id = ec.staff_id)\n" +
                "left join staff sm on(s.staff_manager_id = sm.staff_id)\n" +
                "left join position  p on(p.department_id = d.department_id and ec.position_id =p.position_id) ");

    }
}
