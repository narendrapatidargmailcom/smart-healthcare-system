package com.coder.appointment.controller;

import com.coder.appointment.DTO.AppointmentRequest;
import com.coder.appointment.DTO.GenericsResponse;
import com.coder.appointment.model.Appointment;
import com.coder.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<String> createAppointMent(@RequestBody AppointmentRequest appointmentRequest){

        Long genericsResponse=appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.ok("Appointment created successfully Id->"+genericsResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> findAllAppointMent(){

        List<Appointment> response=appointmentService.getallApointmnet();
        return ResponseEntity.ok(response);
    }



    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> findAllAppointMentByDoctorId(@PathVariable("doctorId") Long doctorId){

        List<Appointment> response=appointmentService.getallApointmnetByDoctorId(doctorId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> findAllAppointMentBypatientId(@PathVariable("patientId") Long patientId){

        List<Appointment> response=appointmentService.getallApointmnetBypatientId(patientId);
        return ResponseEntity.ok(response);
    }
}
