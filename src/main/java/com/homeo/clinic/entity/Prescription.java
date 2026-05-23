package com.homeo.clinic.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Prescription {

    // =====================================
    // ID
    // =====================================

    @Id

    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )

    private Long id;

    // =====================================
    // PATIENT ID
    // =====================================

    @Column(name = "patient_id")

    private Long patientId;

    // =====================================
    // MEDICINE
    // =====================================

    @Column(name = "medicine_name")

    private String medicineName;

    // =====================================
    // DOSAGE
    // =====================================

    private String dosage;

    // =====================================
    // FREQUENCY
    // =====================================

    private String frequency;

    // =====================================
    // DURATION
    // =====================================

    private String duration;

    // =====================================
    // INSTRUCTIONS
    // =====================================

    @Column(columnDefinition = "TEXT")

    private String instructions;

    // =====================================
    // NEXT FOLLOWUP
    // =====================================

    @Column(name = "next_followup_date")

    private LocalDate nextFollowupDate;

    // =====================================
    // CREATED
    // =====================================

    @Column(name = "created_at")

    private LocalDateTime createdAt;
}