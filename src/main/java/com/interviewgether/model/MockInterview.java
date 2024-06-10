package com.interviewgether.model;

import com.interviewgether.model.enums.MockDuration;
import com.interviewgether.model.enums.MockStatus;
import com.interviewgether.model.enums.MockType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
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

    // private Set<Feedback> feedbacks;

}
