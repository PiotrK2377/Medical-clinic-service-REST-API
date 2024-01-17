package com.example.medicalclinic.domain;

import com.example.medicalclinic.enumclass.DoctorSpecialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DOCTORS")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "SPECIALIZATION")
    private DoctorSpecialization specialization;

    @Column(name = "PWZ_NUMBER")
    private String numberPWZ;

    @OneToMany(
          targetEntity = Appointment.class,
          mappedBy = "doctor",
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
    )
    private List<Appointment> appointments;

    @OneToMany(
            targetEntity = Review.class,
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Review> reviews;

    public Doctor(Long id, String name, String lastname, DoctorSpecialization specialization, String numberPWZ) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.specialization = specialization;
        this.numberPWZ = numberPWZ;
    }
}
