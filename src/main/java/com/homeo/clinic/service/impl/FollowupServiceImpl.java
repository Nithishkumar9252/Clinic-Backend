package com.homeo.clinic.service.impl;

import com.homeo.clinic.entity.Followup;

import com.homeo.clinic.repository.FollowupRepository;

import com.homeo.clinic.service.FollowupService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class FollowupServiceImpl
        implements FollowupService {

    // =====================================
    // REPOSITORY
    // =====================================

    private final FollowupRepository
            followupRepository;

    // =====================================
    // SAVE FOLLOWUP USING SP
    // =====================================

    @Override
    public void saveFollowupSP(
            Followup followup
    ) {

        followupRepository
                .saveFollowupSP(

                        followup.getPatientId(),

                        followup.getSymptoms(),

                        followup.getObservations(),

                        followup.getMedicines(),

                        followup.getDoctorNotes(),

                        followup.getImprovementStatus(),

                        followup.getNextFollowupDate()
                );
    }

    // =====================================
    // GET ALL FOLLOWUPS
    // =====================================

    @Override
    public List<Followup>
    getAllFollowups() {

        return followupRepository
                .findAll();
    }

    // =====================================
    // GET FOLLOWUP BY ID
    // =====================================

    @Override
    public Optional<Followup>
    getFollowupById(
            Long id
    ) {

        return followupRepository
                .findById(id);
    }

    // =====================================
    // GET FOLLOWUPS BY PATIENT
    // =====================================

    @Override
    public List<Followup>
    getFollowupsByPatient(
            Long patientId
    ) {

        return followupRepository
                .findByPatientId(
                        patientId
                );
    }

    // =====================================
    // GET BY IMPROVEMENT STATUS
    // =====================================

    @Override
    public List<Followup>
    getByImprovementStatus(
            String status
    ) {

        return followupRepository
                .findByImprovementStatus(
                        status
                );
    }

    // =====================================
    // DELETE FOLLOWUP
    // =====================================

    @Override
    public void deleteFollowup(
            Long id
    ) {

        followupRepository
                .deleteById(id);
    }
}