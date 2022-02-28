package com.truonggiang.employee_management_system.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "staff_information")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
/**
 * Thông tin nhân viên
 */
public class StaffInformation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staff_information_id")
    private Integer staffInformationId;
    @Basic
    @Column(name = "staff_id")
    private Integer staffId;
    @Basic
    @Column(name = "story_name")
    private String storyName;
    @Basic
    @Column(name = "story_experience")
    private String storyExperience;
    @Basic
    @Column(name = "facebook_ulr")
    private String facebookUlr;
    @Basic
    @Column(name = "twitter_url")
    private String twitterUrl;
    @Basic
    @Column(name = "google_url")
    private String googleUrl;
    @Basic
    @Column(name = "linkedin_url")
    private String linkedinUrl;
    @Basic
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @Basic
    @Column(name = "account_number")
    private String accountNumber;
    @Basic
    @Column(name = "bank_ branch")
    private String bankBranch;
    @Basic
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;
    @Basic
    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;
    @Basic
    @Column(name = "emergency_contact_address")
    private String emergencyContactAddress;
    @Basic
    @Column(name = "emergency_contact_email")
    private String emergencyContactEmail;

}
