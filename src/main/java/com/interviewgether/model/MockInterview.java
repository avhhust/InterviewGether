package com.interviewgether.model;

import com.interviewgether.model.enums.MockDuration;
import com.interviewgether.model.enums.MockStatus;
import com.interviewgether.model.enums.MockType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class MockInterview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mock_id")
    private Long mockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mock_session_id")
    private MockSession mockSession;

    // Contains applications and users
    @OneToMany(
            mappedBy = "mockInterview",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MockRegistration> registrations = new HashSet<>();

    @NotNull
    @Column(name = "time_scheduled")
    private OffsetDateTime timeScheduled;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mock_type", length = 13)
    private MockType mockType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mock_duration", length = 17)
    private MockDuration mockDuration;

    @NotNull
    @Column(name = "calendar_event_url")
    private String calendarEventUrl;

    @NotNull
    @Column(name = "doc_file_url")
    private String docFileUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mock_status", length = 11)
    private MockStatus mockStatus;

//    ToDo: map relationship between Feedback and MockInterview
//    Idea is that users leave feedback
//    private Set<Feedback> feedbacks;


    public MockInterview() {
    }

    public MockInterview(MockSession mockSession, Set<MockRegistration> registrations, OffsetDateTime timeScheduled, MockType mockType, MockDuration mockDuration, String calendarEventUrl, String docFileUrl, MockStatus mockStatus) {
        this.mockSession = mockSession;
        this.registrations = registrations;
        this.timeScheduled = timeScheduled;
        this.mockType = mockType;
        this.mockDuration = mockDuration;
        this.calendarEventUrl = calendarEventUrl;
        this.docFileUrl = docFileUrl;
        this.mockStatus = mockStatus;
    }

    public Long getMockId() {
        return mockId;
    }

    public void setMockId(Long mockId) {
        this.mockId = mockId;
    }

    public MockSession getMockSession() {
        return mockSession;
    }

    public void setMockSession(MockSession mockSession) {
        this.mockSession = mockSession;
    }

    public Set<MockRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<MockRegistration> registrations) {
        this.registrations = registrations;
    }

    public OffsetDateTime getTimeScheduled() {
        return timeScheduled;
    }

    public void setTimeScheduled(OffsetDateTime timeScheduled) {
        this.timeScheduled = timeScheduled;
    }

    public MockType getMockType() {
        return mockType;
    }

    public void setMockType(MockType mockType) {
        this.mockType = mockType;
    }

    public MockDuration getMockDuration() {
        return mockDuration;
    }

    public void setMockDuration(MockDuration mockDuration) {
        this.mockDuration = mockDuration;
    }

    public String getCalendarEventUrl() {
        return calendarEventUrl;
    }

    public void setCalendarEventUrl(String calendarEventUrl) {
        this.calendarEventUrl = calendarEventUrl;
    }

    public String getDocFileUrl() {
        return docFileUrl;
    }

    public void setDocFileUrl(String docFileUrl) {
        this.docFileUrl = docFileUrl;
    }

    public MockStatus getMockStatus() {
        return mockStatus;
    }

    public void setMockStatus(MockStatus mockStatus) {
        this.mockStatus = mockStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockInterview that = (MockInterview) o;
        return Objects.equals(mockSession, that.mockSession) && Objects.equals(registrations, that.registrations) && Objects.equals(timeScheduled, that.timeScheduled) && mockType == that.mockType && mockDuration == that.mockDuration && Objects.equals(calendarEventUrl, that.calendarEventUrl) && Objects.equals(docFileUrl, that.docFileUrl) && mockStatus == that.mockStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mockSession, registrations, timeScheduled, mockType, mockDuration, calendarEventUrl, docFileUrl, mockStatus);
    }
}
