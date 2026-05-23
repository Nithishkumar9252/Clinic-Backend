package com.homeo.clinic.service;

import com.homeo.clinic.entity.CaseSheet;

import java.util.List;
import java.util.Optional;

public interface CaseSheetService {

    CaseSheet saveCase(CaseSheet caseSheet);

    List<CaseSheet> getAllCases();

    Optional<CaseSheet> getCaseById(Long id);

    Optional<CaseSheet> getByCaseCode(String caseCode);

    List<CaseSheet> getCasesByPatient(Long patientId);

    List<CaseSheet> getCasesByDoctor(Long doctorId);

    List<CaseSheet> getCasesByStatus(String status);

    void deleteCase(Long id);
}