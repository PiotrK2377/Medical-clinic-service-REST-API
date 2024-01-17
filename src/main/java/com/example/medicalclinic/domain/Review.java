package com.example.medicalclinic.domain;

import com.example.medicalclinic.enumclass.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEWS")
public class Review {

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

    @Column(name = "RATING")
    private Rating rating;

    @Column(name = "COMMENT")
    private String comment;
}
