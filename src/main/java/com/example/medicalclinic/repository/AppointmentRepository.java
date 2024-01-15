package com.example.medicalclinic.repository;

import com.example.medicalclinic.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByAppointmentDate(Date appointmentDate);
}
