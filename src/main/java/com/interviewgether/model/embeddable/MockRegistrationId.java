package com.interviewgether.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MockRegistrationId implements Serializable {
    @Column(name = "mock_interview_id")
    private Long mockInterviewId;

    @Column(name = "participant_id")
    private Long participantId;

    @Column(name = "application_id")
    private Long applicationId;

    public MockRegistrationId() {
    }

    public MockRegistrationId(Long mockInterviewId, Long participantId, Long applicationId) {
        this.mockInterviewId = mockInterviewId;
        this.participantId = participantId;
        this.applicationId = applicationId;
    }

    public Long getMockInterviewId() {
        return mockInterviewId;
    }

    public void setMockInterviewId(Long mockInterviewId) {
        this.mockInterviewId = mockInterviewId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockRegistrationId that = (MockRegistrationId) o;
        return Objects.equals(mockInterviewId, that.mockInterviewId) && Objects.equals(participantId, that.participantId) && Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mockInterviewId, participantId, applicationId);
    }
}
