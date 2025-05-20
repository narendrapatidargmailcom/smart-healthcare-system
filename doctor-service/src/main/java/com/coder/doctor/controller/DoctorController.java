package com.coder.doctor.controller;

import com.coder.doctor.DTO.DoctorRequest;
import com.coder.doctor.DTO.DoctorResponse;
import com.coder.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<DoctorResponse> addDoctor(@RequestBody DoctorRequest doctorRequest){
        DoctorResponse doctorResponse =doctorService.addDoctor(doctorRequest);
        return ResponseEntity.ok(doctorResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> findDoctorfromId(@PathVariable Long id){
        DoctorResponse doctorResponse =doctorService.findDoctorById(id);
        return ResponseEntity.ok(doctorResponse);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DoctorResponse> findDoctorfromEmail(@PathVariable String email){
        DoctorResponse doctorResponse =doctorService.findDoctorByEmail(email);
        return ResponseEntity.ok(doctorResponse);
    }

    @GetMapping()
    public ResponseEntity<List<DoctorResponse>> findAllDoctors(){
        List<DoctorResponse> doctorResponse =doctorService.findAllDoctors();
        return ResponseEntity.ok(doctorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctorfromId(@RequestBody DoctorRequest doctorRequest,@PathVariable Long id){
        DoctorResponse doctorResponse = doctorService.updateDoctor(id,doctorRequest);
        return ResponseEntity.ok(doctorResponse);
    }

}
