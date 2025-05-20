package com.coder.appointment.repository;

import com.coder.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query(value = "SELECT u FROM com.coder.appointment.model.Appointment u WHERE u.doctor_id = :id")
    List<Appointment> getAppointmentByDoctorid(Long id);

    @Query(value = "SELECT u FROM com.coder.appointment.model.Appointment u WHERE u.patient_id = :patientId")
    List<Appointment> getAppointmentByPatientid(Long patientId);
}
