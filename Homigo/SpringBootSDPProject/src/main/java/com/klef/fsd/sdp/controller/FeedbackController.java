package com.klef.fsd.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsd.sdp.model.Feedback;
import com.klef.fsd.sdp.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback) {
        String response = feedbackService.addFeedback(feedback);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable int id) {
        String response = feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editFeedback(@RequestBody Feedback feedback) {
        String response = feedbackService.editFeedback(feedback);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> viewAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.viewAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }
}
