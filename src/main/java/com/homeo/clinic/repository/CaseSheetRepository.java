package com.homeo.clinic.repository;

import com.homeo.clinic.entity.CaseSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CaseSheetRepository extends JpaRepository<CaseSheet, Long> {

    Optional<CaseSheet> findByCaseCode(String caseCode);

    List<CaseSheet> findByPatientId(Long patientId);

    List<CaseSheet> findByDoctorId(Long doctorId);

    List<CaseSheet> findByStatus(String status);
}