package com.coder.appointment.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.util.Date;

@Table(name = "appointment")
@Entity()
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private Long doctor_id;

    //@OneToOne
    //@JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Long patient_id;

    private Date appointmentTime;

    private String doctorComments;

    private  String notes;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }


    public Appointment(Long id, Long doctor_id, Long patient_id, Date appointmentTime, String doctorComments, String notes, AppointmentStatus appointmentStatus) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.appointmentTime = appointmentTime;
        this.doctorComments = doctorComments;
        this.notes = notes;
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment(){}

    public Long getDoctor() {
        return doctor_id;
    }

    public void setDoctor(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getPatient() {
        return patient_id;
    }

    public void setPatient(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDoctorComments() {
        return doctorComments;
    }

    public void setDoctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
