package com.homeo.clinic.service.impl;

import com.homeo.clinic.entity.Prescription;

import com.homeo.clinic.repository.PrescriptionRepository;

import com.homeo.clinic.service.PrescriptionService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PrescriptionServiceImpl
        implements PrescriptionService {

    // =====================================
    // REPOSITORY
    // =====================================

    private final PrescriptionRepository
            prescriptionRepository;

    // =====================================
    // SAVE PRESCRIPTION
    // =====================================

    @Override
    public Prescription savePrescription(
            Prescription prescription
    ) {

        // =====================================
        // CREATED DATE
        // =====================================

        prescription.setCreatedAt(
                LocalDateTime.now()
        );

        // =====================================
        // CALL STORED PROCEDURE
        // =====================================

        prescriptionRepository
                .savePrescriptionSP(

                        prescription.getPatientId(),

                        prescription.getMedicineName(),

                        prescription.getDosage(),

                        prescription.getFrequency(),

                        prescription.getDuration(),

                        prescription.getInstructions(),

                        prescription.getNextFollowupDate()
                );

        return prescription;
    }

    // =====================================
    // GET ALL
    // =====================================

    @Override
    public List<Prescription>
    getAllPrescriptions() {

        return prescriptionRepository
                .findAll();
    }

    // =====================================
    // GET BY ID
    // =====================================

    @Override
    public Optional<Prescription>
    getPrescriptionById(
            Long id
    ) {

        return prescriptionRepository
                .findById(id);
    }

    // =====================================
    // GET BY PATIENT
    // =====================================

    @Override
    public List<Prescription>
    getPrescriptionsByPatient(
            Long patientId
    ) {

        return prescriptionRepository
                .findByPatientId(
                        patientId
                );
    }

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    @Override
    public List<Prescription>
    searchByMedicine(
            String medicineName
    ) {

        return prescriptionRepository
                .findByMedicineNameContainingIgnoreCase(
                        medicineName
                );
    }

    // =====================================
    // DELETE
    // =====================================

    @Override
    public void deletePrescription(
            Long id
    ) {

        prescriptionRepository
                .deleteById(id);
    }
}