package com.homeo.clinic.repository;

import com.homeo.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository
        extends JpaRepository<Patient, Long> {

    Optional<Patient>
    findByPatientCodeIgnoreCase(String patientCode);

    List<Patient>
    findByNameContainingIgnoreCase(String name);

    List<Patient>
    findByPhoneNumberContaining(String phoneNumber);
}