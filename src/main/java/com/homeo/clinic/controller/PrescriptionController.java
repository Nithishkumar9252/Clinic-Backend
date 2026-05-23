package com.homeo.clinic.controller;

import com.homeo.clinic.entity.Prescription;

import com.homeo.clinic.service.PrescriptionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor

@CrossOrigin(origins = "*")

public class PrescriptionController {

    // =====================================
    // SERVICE
    // =====================================

    private final PrescriptionService
            prescriptionService;

    // =====================================
    // CREATE PRESCRIPTION
    // =====================================

    @PostMapping
    public Prescription createPrescription(

            @RequestBody Prescription prescription

    ) {

        return prescriptionService
                .savePrescription(
                        prescription
                );
    }

    // =====================================
    // GET ALL PRESCRIPTIONS
    // =====================================

    @GetMapping
    public List<Prescription>
    getAllPrescriptions() {

        return prescriptionService
                .getAllPrescriptions();
    }

    // =====================================
    // GET PRESCRIPTION BY ID
    // =====================================

    @GetMapping("/{id}")
    public Optional<Prescription>
    getPrescriptionById(

            @PathVariable Long id

    ) {

        return prescriptionService
                .getPrescriptionById(id);
    }

    // =====================================
    // GET PRESCRIPTIONS BY PATIENT
    // =====================================

    @GetMapping("/patient/{patientId}")
    public List<Prescription>
    getPrescriptionsByPatient(

            @PathVariable Long patientId

    ) {

        return prescriptionService
                .getPrescriptionsByPatient(
                        patientId
                );
    }

    // =====================================
    // SEARCH MEDICINE
    // =====================================

    @GetMapping("/search")
    public List<Prescription>
    searchByMedicine(

            @RequestParam String medicineName

    ) {

        return prescriptionService
                .searchByMedicine(
                        medicineName
                );
    }

    // =====================================
    // DELETE PRESCRIPTION
    // =====================================

    @DeleteMapping("/{id}")
    public String deletePrescription(

            @PathVariable Long id

    ) {

        prescriptionService
                .deletePrescription(id);

        return
                "Prescription deleted successfully";
    }
}