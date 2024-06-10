package com.interviewgether.model;

import com.interviewgether.model.embeddable.SolvedProblems;
import com.interviewgether.model.enums.MockType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

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

}
