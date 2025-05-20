package com.coder.patient.controller;

import com.coder.patient.model.Patient;
import com.coder.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping()
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patientRequest){
        Patient patientResponse =patientService.addPatient(patientRequest);
        return ResponseEntity.ok(patientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatientfromId(@PathVariable Long id){
        Patient patientResponse =patientService.findPatientById(id);
        return ResponseEntity.ok(patientResponse);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Patient> findPatientfromEmail(@PathVariable String email){
        Patient patientResponse =patientService.findPatientByEmail(email);
        return ResponseEntity.ok(patientResponse);
    }

    @GetMapping()
    public ResponseEntity<List<Patient>> findAllPatients(){
        List<Patient> patientResponse =patientService.findAllPatients();
        return ResponseEntity.ok(patientResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatientfromId(@RequestBody Patient patientRequest,@PathVariable Long id){
        Patient patientResponse = patientService.updatePatient(id,patientRequest);
        return ResponseEntity.ok(patientResponse);
    }
}
