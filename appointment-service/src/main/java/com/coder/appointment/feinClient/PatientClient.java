package com.coder.appointment.feinClient;


import com.coder.appointment.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service",url = "http://localhost:8083/api/v1/patient")
public interface PatientClient {

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable  Long id);
}
