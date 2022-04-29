package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Staff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staff_id")
    private Integer staffId;
    @Basic
    @Column(name = "staff_no")
    private String staffNo;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "phone_no")
    private String phoneNo;
    @Basic
    @Column(name = "gender")
    private Integer gender;
    @Basic
    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;
    @Basic
    @Column(name = "marital_status")
    private String maritalStatus;
    @Basic
    @Column(name = "province")
    private String province;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "religion")
    private String religion;
    @Basic
    @Column(name = "blood_group")
    private String bloodGroup;
    @Basic
    @Column(name = "nationality")
    private String nationality;
    @Basic
    @Column(name = "identity_card_number")
    private String identityCardNumber;
    @Basic
    @Column(name = "ethnic")
    private String ethnic;
    @Basic
    @Column(name = "place_of_permanent_1")
    private String placeOfPermanent1;
    @Basic
    @Column(name = "place_of_permanent_2")
    private String placeOfPermanent2;
    @Basic
    @Column(name = "status", insertable = false, nullable = false)
    private Integer status;
    @Basic
    @Column(name = "active_flg", insertable = false, nullable = false)
    private Integer activeFlg;
    @Basic
    @Column(name = "created_date", insertable = false, nullable = false)
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_date")
    private Timestamp updatedDate;
    @Basic
    @Column(name = "url_avatar")
    private String urlAvatar;
    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "staff_manager_id")
    private Integer staffManagerId;


    @OneToMany(mappedBy = "staff",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private Collection<EmployeeContract> employeeContracts = new ArrayList<>();

    public void addEmployeeContract(EmployeeContract employeeContract) {
        employeeContracts.add(employeeContract);
        employeeContract.setStaff(this);
    }

}
