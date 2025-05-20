package com.coder.doctor.service;

import com.coder.doctor.DTO.DoctorRequest;
import com.coder.doctor.DTO.DoctorResponse;
import com.coder.doctor.model.Doctor;
import com.coder.doctor.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.swing.tree.RowMapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorResponse addDoctor(DoctorRequest doctorRequest) {

          Doctor mDoctor = modelMapper.map(doctorRequest, Doctor.class);

          Doctor doctor=doctorRepository.save(mDoctor);

          DoctorResponse doctorResponse= modelMapper.map(doctor, DoctorResponse.class);

          return doctorResponse;
    }

    public DoctorResponse findDoctorById(Long id) {
        Doctor doctor=doctorRepository.findById(id).get();
        return  modelMapper.map(doctor,DoctorResponse.class);
    }

    public DoctorResponse findDoctorByEmail(String email) {

        Doctor doctor=doctorRepository.findByEmail(email).get();

        return  modelMapper.map(doctor,DoctorResponse.class);
    }

    public List<DoctorResponse> findAllDoctors() {
        List<DoctorResponse> doctorsResponse = new ArrayList<>();
        List<Doctor> doctors=doctorRepository.findAll();

        for(Doctor doctor:doctors){
             DoctorResponse doctorResponse=modelMapper.map(doctor,DoctorResponse.class);
             doctorsResponse.add(doctorResponse);
        }
        return doctorsResponse;
    }

    public DoctorResponse updateDoctor(Long id, DoctorRequest doctorRequest) {
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setSpeciality(doctorRequest.getSpeciality());
        doctor.setStatus(doctorRequest.getStatus());
        doctor.setYearsOfExperience(doctorRequest.getYearsOfExperience());
        Doctor updatedDoctor=doctorRepository.save(doctor);
        return modelMapper.map(updatedDoctor,DoctorResponse.class);
    }
}
