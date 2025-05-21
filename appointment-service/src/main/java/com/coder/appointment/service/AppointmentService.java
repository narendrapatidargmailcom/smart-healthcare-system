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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AppointmentService {

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    KafkaTemplate<String ,String> kafkaTemplate;

    public Long createAppointment(AppointmentRequest appointmentRequest) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

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

       String appointmentJson = objectMapper.writeValueAsString(appointment);

       sendEventToKafka(appointmentJson);

       return appointment1.getId();
    }

    private void sendEventToKafka(String appointmentJson) {
        String topicName="hospital";
        CompletableFuture<SendResult<String,String>>  completableFuture = kafkaTemplate.send(topicName,appointmentJson);

        completableFuture.whenComplete((result,exception)->{
            if (exception == null){
                RecordMetadata recordMetadata = result.getRecordMetadata();
                System.out.println("Message sent successfully "+topicName);
            }else {
                exception.printStackTrace();
            }
        });
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
