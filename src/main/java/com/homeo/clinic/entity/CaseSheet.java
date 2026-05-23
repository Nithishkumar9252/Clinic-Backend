package com.homeo.clinic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cases")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CaseSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String caseCode;

    // Many cases can belong to one patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Many cases can belong to one doctor/user
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    @Column(columnDefinition = "TEXT")
    private String presentingComplaints;

    @Column(columnDefinition = "TEXT")
    private String historyPresentingIllness;

    @Column(columnDefinition = "TEXT")
    private String pastMedicalHistory;

    @Column(columnDefinition = "TEXT")
    private String familyHistory;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String prescriptionNotes;

    private String status = "ACTIVE";

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}