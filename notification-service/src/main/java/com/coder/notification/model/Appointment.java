package com.coder.notification.model;

import java.util.Date;


public class Appointment {


    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private Patient patient;

    //@OneToOne
    //@JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Doctor doctor;

    private Date appointmentTime;

    private String doctorComments;

    private  String notes;


    private AppointmentStatus appointmentStatus;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment(Long id, Patient patient, Doctor doctor, Date appointmentTime, String doctorComments, String notes, AppointmentStatus appointmentStatus) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.doctorComments = doctorComments;
        this.notes = notes;
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment(){}

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
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
