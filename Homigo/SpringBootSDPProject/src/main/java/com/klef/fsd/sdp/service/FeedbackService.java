package com.klef.fsd.sdp.service;

import java.util.List;

import com.klef.fsd.sdp.model.Feedback;

public interface FeedbackService {
    String addFeedback(Feedback feedback);
    String deleteFeedback(int id);
    String editFeedback(Feedback feedback);
    List<Feedback> viewAllFeedbacks();
}
