package com.example.medicalclinic.service;

import com.example.medicalclinic.domain.Review;
import com.example.medicalclinic.dto.ReviewDto;
import com.example.medicalclinic.exception.ReviewAlreadyExistsException;
import com.example.medicalclinic.exception.ReviewNotFoundException;
import com.example.medicalclinic.mapper.ReviewMapper;
import com.example.medicalclinic.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviewMapper.mapToReviewDtoList(reviews);
    }

    public ReviewDto getReviewById(final Long reviewId) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
        return reviewMapper.mapToReviewDto(review);
    }

    public void createReview(final ReviewDto reviewDto) throws ReviewAlreadyExistsException {
        Review review = reviewMapper.mapToReview(reviewDto);
        boolean isExisting = reviewRepository.existsById(review.getId());
        if (!isExisting) {
            reviewRepository.save(review);
        } else {
            throw new ReviewAlreadyExistsException();
        }
    }

    public ReviewDto updateReview(final Long reviewId, final ReviewDto reviewDto) throws ReviewNotFoundException {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            Review existReview = reviewOptional.get();
            existReview.setRating(reviewDto.getRating());
            existReview.setComment(reviewDto.getComment());
            Review updateReview = reviewRepository.save(existReview);
            return reviewMapper.mapToReviewDto(updateReview);
        } else {
            throw new ReviewNotFoundException();
        }
    }

    public void deleteReview(final Long reviewId) throws ReviewNotFoundException {
        try {
            reviewRepository.deleteById(reviewId);
        } catch (Exception exception) {
            throw new ReviewNotFoundException();
        }
    }
}
