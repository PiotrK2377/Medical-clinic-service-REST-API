package com.example.medicalclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "PESEL_NUMBER")
    private String peselNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @OneToMany(
            targetEntity = Appointment.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Appointment> appointments;

    @OneToMany(
            targetEntity = Review.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Review> reviews;

    public User(Long id, String name, String lastname,String peselNumber, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.peselNumber = peselNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
