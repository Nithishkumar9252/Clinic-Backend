package com.homeo.clinic.repository;

import com.homeo.clinic.entity.Followup;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import java.util.List;

public interface FollowupRepository
        extends JpaRepository<Followup, Long> {

    // =====================================
    // STORED PROCEDURE
    // =====================================

    @Transactional

    @Modifying

    @Query(

            value = """

      CALL save_followup(

        :patientId,

        :symptoms,

        :observations,

        :medicines,

        :doctorNotes,

        :improvementStatus,

        :nextFollowupDate

      )

      """,

            nativeQuery = true
    )

    void saveFollowupSP(

            @Param("patientId")
            Long patientId,

            @Param("symptoms")
            String symptoms,

            @Param("observations")
            String observations,

            @Param("medicines")
            String medicines,

            @Param("doctorNotes")
            String doctorNotes,

            @Param("improvementStatus")
            String improvementStatus,

            @Param("nextFollowupDate")
            LocalDate nextFollowupDate
    );

    // =====================================
    // GET BY PATIENT ID
    // =====================================

    List<Followup>
    findByPatientId(
            Long patientId
    );

    // =====================================
    // GET BY IMPROVEMENT STATUS
    // =====================================

    List<Followup>
    findByImprovementStatus(
            String improvementStatus
    );
}