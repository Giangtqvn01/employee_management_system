package com.truonggiang.employee_management_system.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
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
    @Column(name = " identity_card_number")
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
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "active_flg")
    private Integer activeFlg;
    @Basic
    @Column(name = "created_date")
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

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getPlaceOfPermanent1() {
        return placeOfPermanent1;
    }

    public void setPlaceOfPermanent1(String placeOfPermanent1) {
        this.placeOfPermanent1 = placeOfPermanent1;
    }

    public String getPlaceOfPermanent2() {
        return placeOfPermanent2;
    }

    public void setPlaceOfPermanent2(String placeOfPermanent2) {
        this.placeOfPermanent2 = placeOfPermanent2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(Integer activeFlg) {
        this.activeFlg = activeFlg;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(staffId, staff.staffId) && Objects.equals(staffNo, staff.staffNo) && Objects.equals(firstName, staff.firstName) && Objects.equals(lastName, staff.lastName) && Objects.equals(phoneNo, staff.phoneNo) && Objects.equals(gender, staff.gender) && Objects.equals(dateOfBirth, staff.dateOfBirth) && Objects.equals(maritalStatus, staff.maritalStatus) && Objects.equals(province, staff.province) && Objects.equals(city, staff.city) && Objects.equals(postalCode, staff.postalCode) && Objects.equals(religion, staff.religion) && Objects.equals(bloodGroup, staff.bloodGroup) && Objects.equals(nationality, staff.nationality) && Objects.equals(identityCardNumber, staff.identityCardNumber) && Objects.equals(ethnic, staff.ethnic) && Objects.equals(placeOfPermanent1, staff.placeOfPermanent1) && Objects.equals(placeOfPermanent2, staff.placeOfPermanent2) && Objects.equals(status, staff.status) && Objects.equals(activeFlg, staff.activeFlg) && Objects.equals(createdDate, staff.createdDate) && Objects.equals(updatedDate, staff.updatedDate) && Objects.equals(urlAvatar, staff.urlAvatar) && Objects.equals(email, staff.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, staffNo, firstName, lastName, phoneNo, gender, dateOfBirth, maritalStatus, province, city, postalCode, religion, bloodGroup, nationality, identityCardNumber, ethnic, placeOfPermanent1, placeOfPermanent2, status, activeFlg, createdDate, updatedDate, urlAvatar, email);
    }
}
