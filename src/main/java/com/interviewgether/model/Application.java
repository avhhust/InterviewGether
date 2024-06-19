package com.interviewgether.model;

import com.interviewgether.model.embeddable.SolvedProblems;
import com.interviewgether.model.enums.MockType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "mock_session_id")
    private MockSession mockSession;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MockType mockType;

    @NotNull
    @Embedded
    private SolvedProblems solvedProblems;

    @OneToOne(mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Preference preferences;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Application() {
    }

    public Application(long id, UserAccount userAccount, MockSession mockSession, MockType mockType, SolvedProblems solvedProblems, Preference preferences, OffsetDateTime createdAt) {
        this.userAccount = userAccount;
        this.mockSession = mockSession;
        this.mockType = mockType;
        this.solvedProblems = solvedProblems;
        this.preferences = preferences;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public MockSession getMockSession() {
        return mockSession;
    }

    public void setMockSession(MockSession mockSession) {
        this.mockSession = mockSession;
    }

    public MockType getMockType() {
        return mockType;
    }

    public void setMockType(MockType mockType) {
        this.mockType = mockType;
    }

    public SolvedProblems getSolvedProblems() {
        return solvedProblems;
    }

    public void setSolvedProblems(SolvedProblems solvedProblems) {
        this.solvedProblems = solvedProblems;
    }

    public Preference getPreferences() {
        return preferences;
    }

    public void setPreferences(Preference preferences) {
        this.preferences = preferences;
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
        Application that = (Application) o;
        return Objects.equals(userAccount, that.userAccount) && Objects.equals(mockSession, that.mockSession) && mockType == that.mockType && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, mockSession, mockType, createdAt);
    }
}
