package com.homeo.clinic.service.impl;

import com.homeo.clinic.entity.Patient;
import com.homeo.clinic.repository.PatientRepository;
import com.homeo.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PatientServiceImpl
        implements PatientService {

    private final PatientRepository
            patientRepository;

    // SAVE PATIENT

    @Override
    public Patient savePatient(
            Patient patient
    ) {

    /*
    =====================================
    NEW PATIENT
    =====================================
    */

        if (patient.getId() == null) {

            long count =
                    patientRepository.count();

            long nextNumber =
                    count + 1;

            String patientCode =
                    String.format(
                            "PAT-%04d",
                            nextNumber
                    );

            patient.setPatientCode(
                    patientCode
            );
        }

    /*
    =====================================
    SAVE
    =====================================
    */

        return patientRepository.save(
                patient
        );
    }

    // GET ALL

    @Override
    public List<Patient> getAllPatients() {

        return patientRepository.findAll();
    }

    // GET BY ID

    @Override
    public Optional<Patient>
    getPatientById(Long id) {

        return patientRepository.findById(id);
    }

    // GET BY CODE

    @Override
    public Optional<Patient>
    getByPatientCode(
            String patientCode
    ) {

        return patientRepository
                .findByPatientCodeIgnoreCase(
                        patientCode
                );
    }

    // SEARCH NAME

    @Override
    public List<Patient>
    searchByName(String name) {

        return patientRepository
                .findByNameContainingIgnoreCase(
                        name
                );
    }

    // SEARCH PHONE

    @Override
    public List<Patient>
    searchByPhone(
            String phoneNumber
    ) {

        return patientRepository
                .findByPhoneNumberContaining(
                        phoneNumber
                );
    }

    // DELETE

    @Override
    public void deletePatient(Long id) {

        patientRepository.deleteById(id);
    }
}