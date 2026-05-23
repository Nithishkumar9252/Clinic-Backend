package com.homeo.clinic.controller;

import com.homeo.clinic.entity.CaseSheet;
import com.homeo.clinic.service.CaseSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor

public class CaseSheetController {

    private final CaseSheetService caseSheetService;

    @PostMapping
    public CaseSheet createCase(@RequestBody CaseSheet caseSheet) {
        return caseSheetService.saveCase(caseSheet);
    }

    @GetMapping
    public List<CaseSheet> getAllCases() {
        return caseSheetService.getAllCases();
    }

    @GetMapping("/{id}")
    public Optional<CaseSheet> getCaseById(@PathVariable Long id) {
        return caseSheetService.getCaseById(id);
    }

    @GetMapping("/code/{caseCode}")
    public Optional<CaseSheet> getByCaseCode(
            @PathVariable String caseCode) {

        return caseSheetService.getByCaseCode(caseCode);
    }

    @GetMapping("/patient/{patientId}")
    public List<CaseSheet> getCasesByPatient(
            @PathVariable Long patientId) {

        return caseSheetService.getCasesByPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<CaseSheet> getCasesByDoctor(
            @PathVariable Long doctorId) {

        return caseSheetService.getCasesByDoctor(doctorId);
    }

    @GetMapping("/status/{status}")
    public List<CaseSheet> getCasesByStatus(
            @PathVariable String status) {

        return caseSheetService.getCasesByStatus(status);
    }

    @DeleteMapping("/{id}")
    public String deleteCase(@PathVariable Long id) {

        caseSheetService.deleteCase(id);

        return "Case deleted successfully";
    }
}