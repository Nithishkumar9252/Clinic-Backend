package com.homeo.clinic.service;

import com.homeo.clinic.entity.Prescription;

import java.util.List;

import java.util.Optional;

public interface PrescriptionService {

    // =====================================
    // SAVE
    // =====================================

    Prescription savePrescription(
            Prescription prescription
    );

    // =====================================
    // GET ALL
    // =====================================

    List<Prescription>
    getAllPrescriptions();

    // =====================================
    // GET BY ID
    // =====================================

    Optional<Prescription>
    getPrescriptionById(
            Long id
    );

    // =====================================
    // GET BY PATIENT
    // =====================================

    List<Prescription>
    getPrescriptionsByPatient(
            Long patientId
    );

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    List<Prescription>
    searchByMedicine(
            String medicineName
    );

    // =====================================
    // DELETE
    // =====================================

    void deletePrescription(
            Long id
    );
}