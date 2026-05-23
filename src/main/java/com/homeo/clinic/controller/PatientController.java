package com.homeo.clinic.controller;

import com.homeo.clinic.entity.Patient;
import com.homeo.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor

@CrossOrigin(origins = "*")

public class PatientController {

    private final PatientService
            patientService;

    // CREATE

    @PostMapping
    public Patient createPatient(
            @RequestBody Patient patient
    ) {

        return patientService
                .savePatient(patient);
    }

    // GET ALL

    @GetMapping
    public List<Patient>
    getAllPatients() {

        return patientService
                .getAllPatients();
    }

    // GET BY ID

    @GetMapping("/{id}")
    public Optional<Patient>
    getPatientById(
            @PathVariable Long id
    ) {

        return patientService
                .getPatientById(id);
    }

    // GET BY PATIENT CODE

    @GetMapping("/code/{patientCode}")
    public Optional<Patient>
    getByPatientCode(
            @PathVariable String patientCode
    ) {

        return patientService
                .getByPatientCode(
                        patientCode
                );
    }

    // SEARCH NAME

    @GetMapping("/search/name")
    public List<Patient>
    searchByName(
            @RequestParam String name
    ) {

        return patientService
                .searchByName(name);
    }

    // SEARCH PHONE

    @GetMapping("/search/phone")
    public List<Patient>
    searchByPhone(
            @RequestParam String phoneNumber
    ) {

        return patientService
                .searchByPhone(phoneNumber);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public String deletePatient(
            @PathVariable Long id
    ) {

        patientService.deletePatient(id);

        return "Patient deleted successfully";
    }

    // UPDATE PATIENT

    @PutMapping("/{id}")

    public Patient updatePatient(

            @PathVariable Long id,

            @RequestBody Patient patient

    ) {

        patient.setId(id);

        return patientService.savePatient(
                patient
        );
    }
}