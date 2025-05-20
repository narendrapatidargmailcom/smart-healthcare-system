package com.coder.appointment.DTO;


import java.util.Date;

public class AppointmentRequest {

        private String doctorId;
        private String patientId;
        private Date appointmentTime;
        private String doctorComments;
        private String  notes;

    public AppointmentRequest(String doctorId, String patientId, Date appointmentTime, String doctorComments, String notes) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentTime = appointmentTime;
        this.doctorComments = doctorComments;
        this.notes = notes;
    }

    public AppointmentRequest(){}

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
}
