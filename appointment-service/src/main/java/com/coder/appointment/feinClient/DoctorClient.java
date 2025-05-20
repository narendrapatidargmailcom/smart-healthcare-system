package com.coder.appointment.feinClient;

import com.coder.appointment.model.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-client",url = "http://localhost:8082/api/v1/doctor")
public interface DoctorClient {

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id);
}
