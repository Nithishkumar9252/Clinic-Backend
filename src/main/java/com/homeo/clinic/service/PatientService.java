package com.homeo.clinic.service;

import com.homeo.clinic.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    // SAVE

    Patient savePatient(
            Patient patient
    );

    // GET ALL

    List<Patient> getAllPatients();

    // GET BY ID

    Optional<Patient> getPatientById(
            Long id
    );

    // GET BY CODE

    Optional<Patient> getByPatientCode(
            String patientCode
    );

    // SEARCH NAME

    List<Patient> searchByName(
            String name
    );

    // SEARCH PHONE

    List<Patient> searchByPhone(
            String phoneNumber
    );

    // DELETE

    void deletePatient(
            Long id
    );
}