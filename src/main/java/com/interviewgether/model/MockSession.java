package com.interviewgether.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.*;

@Entity
public class MockSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mockSessionId;

    @Column(name = "is_active")
    private boolean isActive;

    @NotNull
    @Column(name = "start_date_time")
    private OffsetDateTime startDateTime;

    @NotNull
    @Column(name = "end_date_time")
    private OffsetDateTime endDateTime;

    @OneToMany(mappedBy = "mockSession",
            fetch = FetchType.LAZY
    )
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "mockSession",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<MockInterview> mockInterviews = new HashSet<>();

    public MockSession() {
    }

    public MockSession(long mockSessionId, boolean isActive, OffsetDateTime startDateTime, OffsetDateTime endDateTime, List<Application> applications, Set<MockInterview> mockInterviews) {
        this.mockSessionId = mockSessionId;
        this.isActive = isActive;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.applications = applications;
        this.mockInterviews = mockInterviews;
    }

    public long getMockSessionId() {
        return mockSessionId;
    }

    public void setMockSessionId(long mockSessionId) {
        this.mockSessionId = mockSessionId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public OffsetDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(OffsetDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public OffsetDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(OffsetDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Set<MockInterview> getMockInterviews() {
        return mockInterviews;
    }

    public void setMockInterviews(Set<MockInterview> mockInterviews) {
        this.mockInterviews = mockInterviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockSession that = (MockSession) o;
        return mockSessionId == that.mockSessionId && isActive == that.isActive && Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime) && Objects.equals(applications, that.applications) && Objects.equals(mockInterviews, that.mockInterviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mockSessionId, isActive, startDateTime, endDateTime, applications, mockInterviews);
    }
}
