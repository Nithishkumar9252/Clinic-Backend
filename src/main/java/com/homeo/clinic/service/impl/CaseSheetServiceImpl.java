package com.homeo.clinic.service.impl;

import com.homeo.clinic.entity.CaseSheet;
import com.homeo.clinic.repository.CaseSheetRepository;
import com.homeo.clinic.service.CaseSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CaseSheetServiceImpl implements CaseSheetService {

    private final CaseSheetRepository caseSheetRepository;

    @Override
    public CaseSheet saveCase(CaseSheet caseSheet) {

        // Auto Generate Case Code
        if (caseSheet.getCaseCode() == null ||
                caseSheet.getCaseCode().isEmpty()) {

            long count = caseSheetRepository.count() + 1;

            caseSheet.setCaseCode("CASE-" + (1000 + count));
        }

        return caseSheetRepository.save(caseSheet);
    }

    @Override
    public List<CaseSheet> getAllCases() {
        return caseSheetRepository.findAll();
    }

    @Override
    public Optional<CaseSheet> getCaseById(Long id) {
        return caseSheetRepository.findById(id);
    }

    @Override
    public Optional<CaseSheet> getByCaseCode(String caseCode) {
        return caseSheetRepository.findByCaseCode(caseCode);
    }

    @Override
    public List<CaseSheet> getCasesByPatient(Long patientId) {
        return caseSheetRepository.findByPatientId(patientId);
    }

    @Override
    public List<CaseSheet> getCasesByDoctor(Long doctorId) {
        return caseSheetRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<CaseSheet> getCasesByStatus(String status) {
        return caseSheetRepository.findByStatus(status);
    }

    @Override
    public void deleteCase(Long id) {
        caseSheetRepository.deleteById(id);
    }
}