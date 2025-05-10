package com.klef.fsd.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.Feedback;
import com.klef.fsd.sdp.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public String addFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
        return "Feedback added successfully";
    }

    @Override
    public String deleteFeedback(int id) {
        Optional<Feedback> existing = feedbackRepository.findById(id);
        if (existing.isPresent()) {
            feedbackRepository.deleteById(id);
            return "Feedback deleted successfully";
        } else {
            return "Feedback not found";
        }
    }

    @Override
    public String editFeedback(Feedback feedback) {
        Optional<Feedback> existing = feedbackRepository.findById(feedback.getId());
        if (existing.isPresent()) {
            feedbackRepository.save(feedback);
            return "Feedback updated successfully";
        } else {
            return "Feedback not found";
        }
    }

    @Override
    public List<Feedback> viewAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}
