package com.example.medicalclinic.domain;

import com.example.medicalclinic.enumclass.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @Column(name = "APPOINTMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;

    @Column(name = "STATUS")
    private AppointmentStatus status;
}
