package com.coder.appointment.service;

import com.coder.appointment.DTO.AppointmentRequest;
import com.coder.appointment.DTO.GenericsResponse;
import com.coder.appointment.feinClient.DoctorClient;
import com.coder.appointment.feinClient.PatientClient;
import com.coder.appointment.model.Appointment;
import com.coder.appointment.model.AppointmentStatus;
import com.coder.appointment.model.Doctor;
import com.coder.appointment.model.Patient;
import com.coder.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Long createAppointment(AppointmentRequest appointmentRequest) {

        Patient patient = patientClient.getPatientById(Long.parseLong(appointmentRequest.getPatientId()));

        Doctor doctor =doctorClient.getDoctorById(Long.parseLong(appointmentRequest.getDoctorId()));
        Appointment appointment = new Appointment();

        appointment.setDoctor(doctor.getId());

        appointment.setPatient(patient.getId());

        appointment.setNotes(appointmentRequest.getNotes());

        appointment.setDoctorComments(appointmentRequest.getDoctorComments());

        appointment.setAppointmentTime(appointmentRequest.getAppointmentTime());

        appointment.setAppointmentStatus(AppointmentStatus.PENDING);

       Appointment appointment1= appointmentRepository.save(appointment);

       System.out.println(appointment1);

       return appointment1.getId();
    }

    public List<Appointment> getallApointmnet() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getallApointmnetByDoctorId(Long id) {
        return appointmentRepository.getAppointmentByDoctorid(id);
    }

    public List<Appointment> getallApointmnetBypatientId(Long patientId) {
        return appointmentRepository.getAppointmentByPatientid(patientId);
    }
}
