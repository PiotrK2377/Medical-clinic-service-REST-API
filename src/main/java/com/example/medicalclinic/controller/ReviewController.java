package com.example.medicalclinic.controller;

import com.example.medicalclinic.dto.ReviewDto;
import com.example.medicalclinic.exception.ReviewAlreadyExistsException;
import com.example.medicalclinic.exception.ReviewNotFoundException;
import com.example.medicalclinic.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/med/reviews")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService service;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviews() {
        List<ReviewDto> reviews = service.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long reviewId) throws ReviewNotFoundException {
        return ResponseEntity.ok(service.getReviewById(reviewId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReview(@RequestBody ReviewDto reviewDto) {
        try {
            service.createReview(reviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ReviewAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto) {
        try {
            ReviewDto updateReview = service.updateReview(reviewId, reviewDto);
            return ResponseEntity.ok(updateReview);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        try {
            service.deleteReview(reviewId);
            return ResponseEntity.ok("Delete Review with ID number: " + reviewId);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
