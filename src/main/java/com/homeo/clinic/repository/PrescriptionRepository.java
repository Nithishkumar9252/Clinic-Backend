package com.homeo.clinic.repository;

import com.homeo.clinic.entity.Prescription;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import java.util.List;

public interface PrescriptionRepository
        extends JpaRepository<Prescription, Long> {

    // =====================================
    // GET BY PATIENT
    // =====================================

    List<Prescription>
    findByPatientId(
            Long patientId
    );

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    List<Prescription>
    findByMedicineNameContainingIgnoreCase(
            String medicineName
    );

    // =====================================
    // STORED PROCEDURE
    // =====================================

    @Transactional

    @Modifying

    @Query(

            value =
                    "CALL save_prescription(" +
                            ":patientId," +
                            ":medicineName," +
                            ":dosage," +
                            ":frequency," +
                            ":duration," +
                            ":instructions," +
                            ":nextFollowupDate)",

            nativeQuery = true
    )

    void savePrescriptionSP(

            @Param("patientId")
            Long patientId,

            @Param("medicineName")
            String medicineName,

            @Param("dosage")
            String dosage,

            @Param("frequency")
            String frequency,

            @Param("duration")
            String duration,

            @Param("instructions")
            String instructions,

            @Param("nextFollowupDate")
            LocalDate nextFollowupDate
    );
}