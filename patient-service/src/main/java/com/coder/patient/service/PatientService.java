package com.coder.patient.service;

import com.coder.patient.model.Patient;
import com.coder.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findPatientByEmail(String email) {

       return patientRepository.findByEmail(email).get();
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).get();
    }

    public Patient addPatient(Patient patientRequest) {
        return  patientRepository.save(patientRequest);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Long id, Patient patientRequest) {
       Patient patient= patientRepository.findById(id).get();

        patient.setEmail(patientRequest.getEmail());
        patient.setFirstName(patientRequest.getFirstName());
        patient.setLastName(patientRequest.getLastName());
        patient.setPhone(patientRequest.getPhone());
        patient.setAge(patientRequest.getAge());

        Patient updatedPatient=patientRepository.save(patient);
        return updatedPatient;
    }
}
