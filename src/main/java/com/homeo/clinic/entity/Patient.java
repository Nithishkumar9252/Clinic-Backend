package com.homeo.clinic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patients")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // PATIENT CODE

    @Column(unique = true, nullable = false)
    private String patientCode;

    // BASIC DETAILS

    private String name;

    private Integer age;

    private String sex;

    private String maritalStatus;

    private String dswOf;

    @Column(unique = true)
    private String phoneNumber;

    private String occupation;

    private String religion;

    @Column(length = 3000)
    private String address;

    private String aadhaarNo;

    // CASE SHEET

    @Column(length = 5000)
    private String presentingComplaints;

    @Column(length = 5000)
    private String historyPresentIllness;

    @Column(length = 5000)
    private String pastMedicalHistory;

    @Column(length = 5000)
    private String familyHistory;

    @Column(length = 5000)
    private String lifeSpaceInvestigation;

    // PHYSICAL GENERALS

    private String appetite;

    private String thirst;

    private String stool;

    private String urine;

    private String desires;

    private String aversions;

    private String sleep;

    private String dreams;

    private String sweat;

    private String habits;

    private String menses;

    private String obstetric;

    private String thermals;

    // MENTAL GENERALS

    @Column(length = 5000)
    private String mentalGenerals;

    // PHYSICAL EXAMINATION

    private String pulse;

    private String bp;

    private String wt;

    private String temp;

    private String rr;

    private String pallor;

    // SYSTEMIC EXAMINATION

    @Column(length = 5000)
    private String systemicExamination;

    // INVESTIGATIONS

    @Column(length = 5000)
    private String investigations;

    // DIAGNOSIS

    @Column(length = 5000)
    private String diagnosis;

    // REPERTORIAL TOTALITY

    @Column(length = 5000)
    private String repertorialTotality;

    // RX

    @Column(length = 5000)
    private String rx;

    // FOLLOW UP

    @Column(length = 5000)

    private String followUpDate;

    @Column(length = 5000)
    private String followUpNotes;

    // ACTIVE STATUS

    @Builder.Default
    private Boolean active = true;

    // CREATED DATE

    @Builder.Default
    private LocalDateTime createdAt =
            LocalDateTime.now();
}