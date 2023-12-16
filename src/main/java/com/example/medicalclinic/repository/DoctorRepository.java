package com.example.medicalclinic.repository;

import com.example.medicalclinic.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    boolean existsDoctorByNumberPWZ(String numberPWZ);
}
