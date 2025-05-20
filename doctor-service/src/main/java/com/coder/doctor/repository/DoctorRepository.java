package com.coder.doctor.repository;

import com.coder.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByEmail(String email);
}
