package com.example.medicalclinic.repository;

import com.example.medicalclinic.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByPeselNumber(String peselNumber);
}
