package com.interviewgether.model;

import com.interviewgether.model.embeddable.FeedbackRatingId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

import java.util.Objects;

@Entity
public class FeedbackRating {

    @EmbeddedId
    private FeedbackRatingId feedbackRatingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("feedbackId")
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ratingAspectId")
    @JoinColumn(name = "rating_aspect_id")
    private RatingAspect ratingAspect;

    @Max(10)
    @Column(name = "rating_value")
    private Byte ratingValue;

    public FeedbackRating() {
    }

    public FeedbackRating(FeedbackRatingId feedbackRatingId, Feedback feedback, RatingAspect ratingAspect, Byte ratingValue) {
        this.feedbackRatingId = feedbackRatingId;
        this.feedback = feedback;
        this.ratingAspect = ratingAspect;
        this.ratingValue = ratingValue;
    }

    public FeedbackRatingId getFeedbackRatingId() {
        return feedbackRatingId;
    }

    public void setFeedbackRatingId(FeedbackRatingId feedbackRatingId) {
        this.feedbackRatingId = feedbackRatingId;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public RatingAspect getRatingAspect() {
        return ratingAspect;
    }

    public void setRatingAspect(RatingAspect ratingAspect) {
        this.ratingAspect = ratingAspect;
    }

    public Byte getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Byte ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackRating that = (FeedbackRating) o;
        return Objects.equals(feedbackRatingId, that.feedbackRatingId) && Objects.equals(feedback, that.feedback) && Objects.equals(ratingAspect, that.ratingAspect) && Objects.equals(ratingValue, that.ratingValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackRatingId, feedback, ratingAspect, ratingValue);
    }
}
