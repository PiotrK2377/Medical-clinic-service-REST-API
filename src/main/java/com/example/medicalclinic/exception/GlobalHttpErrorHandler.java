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
        return new ResponseEntity<>("User with given pesel number already exist.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Object> handleDoctorNotFoundException (DoctorNotFoundException exception) {
        return new ResponseEntity<>("Doctor with given ID doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorAlreadyExistsException.class)
    public ResponseEntity<Object> handleDoctorAlreadyExistsException (DoctorAlreadyExistsException exception) {
        return new ResponseEntity<>("Doctor with given PWZ already exist.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<Object> handleAppointmentNotFoundException (AppointmentNotFoundException exception) {
        return new ResponseEntity<>("Appointment with given ID doesn't exist",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentAlreadyExistsException.class)
    public ResponseEntity<Object> handleAppointmentAlreadyExistsException (AppointmentAlreadyExistsException exception) {
        return new ResponseEntity<>("Appointment with given ID already exist", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<Object> handleReviewNotFoundException (ReviewNotFoundException exception) {
        return new ResponseEntity<>("Review with given ID dosen't exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewAlreadyExistsException.class)
    public ResponseEntity<Object> handleReviewAlreadyExistsException (ReviewAlreadyExistsException exception) {
        return new ResponseEntity<>("Review with given ID already exist", HttpStatus.CONFLICT);
    }
}
