package com.homeo.clinic.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "followups")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Followup {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )

    private Long id;

    // =====================================
    // PATIENT ID
    // =====================================

    private Long patientId;

    // =====================================
    // FOLLOWUP DETAILS
    // =====================================

    @Column(columnDefinition = "TEXT")
    private String symptoms;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Column(columnDefinition = "TEXT")
    private String medicines;

    @Column(columnDefinition = "TEXT")
    private String doctorNotes;

    private String improvementStatus;

    private LocalDate nextFollowupDate;

    // =====================================
    // CREATED DATE
    // =====================================

    @Builder.Default

    private LocalDateTime createdAt =
            LocalDateTime.now();
}