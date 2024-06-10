package com.interviewgether.model;

import com.interviewgether.model.embeddable.MockRegistrationId;
import com.interviewgether.model.enums.ParticipantRole;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class MockRegistration {

    @EmbeddedId
    private MockRegistrationId mockRegistrationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mockInterviewId")
    @JoinColumn(name = "mock_interview_id")
    private MockInterview mockInterview;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("participantId")
    @JoinColumn(name = "participant_id")
    private UserAccount participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("applicationId")
    @JoinColumn(name = "application_id")
    private Application application;

    @Enumerated(EnumType.STRING)
    @Column(name = "mock_role", length = 10)
    private ParticipantRole participantRole;

    public MockRegistration() {
    }

    public MockRegistration(MockRegistrationId mockRegistrationId, MockInterview mockInterview, UserAccount participant, Application application, ParticipantRole participantRole) {
        this.mockRegistrationId = mockRegistrationId;
        this.mockInterview = mockInterview;
        this.participant = participant;
        this.application = application;
        this.participantRole = participantRole;
    }

    public MockRegistrationId getMockRegistrationId() {
        return mockRegistrationId;
    }

    public void setMockRegistrationId(MockRegistrationId mockRegistrationId) {
        this.mockRegistrationId = mockRegistrationId;
    }

    public MockInterview getMockInterview() {
        return mockInterview;
    }

    public void setMockInterview(MockInterview mockInterview) {
        this.mockInterview = mockInterview;
    }

    public UserAccount getParticipant() {
        return participant;
    }

    public void setParticipant(UserAccount participant) {
        this.participant = participant;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ParticipantRole getParticipantRole() {
        return participantRole;
    }

    public void setParticipantRole(ParticipantRole participantRole) {
        this.participantRole = participantRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockRegistration that = (MockRegistration) o;
        return Objects.equals(mockRegistrationId, that.mockRegistrationId) && Objects.equals(mockInterview, that.mockInterview) && Objects.equals(participant, that.participant) && Objects.equals(application, that.application) && participantRole == that.participantRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mockRegistrationId, mockInterview, participant, application, participantRole);
    }
}
