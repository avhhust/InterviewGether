package com.interviewgether.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserAccount sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserAccount receiver;

    @OneToMany(mappedBy = "feedback",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<FeedbackRating> ratings = new HashSet<>();

    @Column
    private String comment;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Feedback() {
    }

    public Feedback(Long feedbackId, UserAccount sender, UserAccount receiver, Set<FeedbackRating> ratings, String comment, OffsetDateTime createdAt) {
        this.feedbackId = feedbackId;
        this.sender = sender;
        this.receiver = receiver;
        this.ratings = ratings;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    public Set<FeedbackRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<FeedbackRating> ratings) {
        this.ratings = ratings;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(sender, feedback.sender) && Objects.equals(receiver, feedback.receiver) && Objects.equals(ratings, feedback.ratings) && Objects.equals(comment, feedback.comment) && Objects.equals(createdAt, feedback.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, ratings, comment, createdAt);
    }
}
