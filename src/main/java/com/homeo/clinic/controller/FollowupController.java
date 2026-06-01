package com.homeo.clinic.controller;

import com.homeo.clinic.entity.Followup;

import com.homeo.clinic.repository.FollowupRepository;

import com.homeo.clinic.service.FollowupService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/followups")
@RequiredArgsConstructor

@CrossOrigin(origins = "*")

public class FollowupController {

    // =====================================
    // SERVICE
    // =====================================

    private final FollowupService
            followupService;

    // =====================================
    // REPOSITORY
    // =====================================

    private final FollowupRepository
            followupRepository;

    // =====================================
    // CREATE FOLLOWUP
    // =====================================

    @PostMapping
    public String createFollowup(
            @RequestBody Followup followup
    ) {

        followupService
                .saveFollowupSP(
                        followup
                );

        return
                "Followup saved successfully";
    }

    // =====================================
    // UPDATE FOLLOWUP
    // =====================================

    @PutMapping("/{id}")

    public String updateFollowup(

            @PathVariable Long id,

            @RequestBody Followup updatedFollowup

    ) {

        Optional<Followup> optionalFollowup =

                followupService
                        .getFollowupById(id);

        // =====================================
        // FOLLOWUP NOT FOUND
        // =====================================

        if(optionalFollowup.isEmpty()){

            return
                    "Followup not found";
        }

        // =====================================
        // EXISTING FOLLOWUP
        // =====================================

        Followup existing =
                optionalFollowup.get();

        // =====================================
        // UPDATE FIELDS
        // =====================================

        existing.setPatientId(
                updatedFollowup.getPatientId()
        );

        existing.setSymptoms(
                updatedFollowup.getSymptoms()
        );

        existing.setObservations(
                updatedFollowup.getObservations()
        );

        existing.setMedicines(
                updatedFollowup.getMedicines()
        );

        existing.setDoctorNotes(
                updatedFollowup.getDoctorNotes()
        );

        existing.setImprovementStatus(
                updatedFollowup.getImprovementStatus()
        );

        existing.setNextFollowupDate(
                updatedFollowup.getNextFollowupDate()
        );

        // =====================================
        // SAVE UPDATED FOLLOWUP
        // =====================================

        followupRepository.save(
                existing
        );

        return
                "Followup updated successfully";
    }

    // =====================================
    // GET ALL FOLLOWUPS
    // =====================================

    @GetMapping
    public List<Followup>
    getAllFollowups() {

        return followupService
                .getAllFollowups();
    }

    // =====================================
    // GET FOLLOWUP BY ID
    // =====================================

    @GetMapping("/{id}")
    public Optional<Followup>
    getFollowupById(
            @PathVariable Long id
    ) {

        return followupService
                .getFollowupById(id);
    }

    // =====================================
    // GET FOLLOWUPS BY PATIENT
    // =====================================

    @GetMapping("/patient/{patientId}")
    public List<Followup>
    getFollowupsByPatient(
            @PathVariable Long patientId
    ) {

        return followupService
                .getFollowupsByPatient(
                        patientId
                );
    }

    // =====================================
    // GET BY STATUS
    // =====================================

    @GetMapping("/status/{status}")
    public List<Followup>
    getByImprovementStatus(
            @PathVariable String status
    ) {

        return followupService
                .getByImprovementStatus(
                        status
                );
    }

    // =====================================
    // DELETE FOLLOWUP
    // =====================================

    @DeleteMapping("/{id}")
    public String deleteFollowup(
            @PathVariable Long id
    ) {

        followupService
                .deleteFollowup(id);

        return
                "Followup deleted successfully";
    }
}