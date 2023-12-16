package com.example.medicalclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException (UserNotFoundException exception) {
        return new ResponseEntity<>("User with given ID doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException (UserAlreadyExistsException exception) {
        return new ResponseEntity<>("User with given pesel number already exists.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Object> handleDoctorNotFoundException (DoctorNotFoundException exception) {
        return new ResponseEntity<>("Doctor with given ID doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorAlreadyExistsException.class)
    public ResponseEntity<Object> handleDoctorAlreadyExistsException (DoctorAlreadyExistsException exception) {
        return new ResponseEntity<>("Doctor with given PWZ aready exists.", HttpStatus.CONFLICT);
    }
}
