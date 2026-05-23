package com.homeo.clinic.service;

import com.homeo.clinic.entity.Followup;

import java.util.List;
import java.util.Optional;

public interface FollowupService {

    // =====================================
    // SAVE FOLLOWUP USING SP
    // =====================================

    void saveFollowupSP(
            Followup followup
    );

    // =====================================
    // GET ALL FOLLOWUPS
    // =====================================

    List<Followup>
    getAllFollowups();

    // =====================================
    // GET FOLLOWUP BY ID
    // =====================================

    Optional<Followup>
    getFollowupById(
            Long id
    );

    // =====================================
    // GET FOLLOWUPS BY PATIENT
    // =====================================

    List<Followup>
    getFollowupsByPatient(
            Long patientId
    );

    // =====================================
    // GET BY STATUS
    // =====================================

    List<Followup>
    getByImprovementStatus(
            String status
    );

    // =====================================
    // DELETE FOLLOWUP
    // =====================================

    void deleteFollowup(
            Long id
    );
}