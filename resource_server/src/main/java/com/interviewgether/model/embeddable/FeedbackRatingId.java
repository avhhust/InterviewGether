package com.interviewgether.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FeedbackRatingId implements Serializable {
    @Column(name = "feedback_id")
    private Long feedbackId;

    @Column(name = "rating_aspect_id")
    private Long ratingAspectId;

    public FeedbackRatingId() {}

    public FeedbackRatingId(Long feedbackId, Long ratingAspectId) {
        this.feedbackId = feedbackId;
        this.ratingAspectId = ratingAspectId;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getRatingAspectId() {
        return ratingAspectId;
    }

    public void setRatingAspectId(Long ratingAspectId) {
        this.ratingAspectId = ratingAspectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackRatingId that = (FeedbackRatingId) o;
        return Objects.equals(feedbackId, that.feedbackId) && Objects.equals(ratingAspectId, that.ratingAspectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, ratingAspectId);
    }
}
