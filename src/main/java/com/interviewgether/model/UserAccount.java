package com.interviewgether.model;

import com.interviewgether.model.embeddable.SocialNetwork;
import com.interviewgether.model.enums.ProficiencyLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

@Entity
public class UserAccount {

    @Id
    private Long id;

    // Allows UserAccount share the same ID with User. So user_id becomes primary key for UserAccount
    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "number_completed_mocks", nullable = false)
    private Integer numberCompletedMocks;

    @Enumerated
    @Column(name = "proficiency_level", length = 12)
    private ProficiencyLevel proficiencyLevel;

    @ElementCollection
    @CollectionTable(
            name = "social_networks",
            joinColumns = @JoinColumn(name = "user_id")
    )
    private Set<SocialNetwork> socialNetworks = new HashSet<>();

    @OneToMany(mappedBy = "participant",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MockRegistration> mockInterviews = new HashSet<>();

    @OneToMany(mappedBy = "userAccount",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Feedback> receivedFeedback;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Feedback> sentFeedback;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public UserAccount() {

    }

    public UserAccount(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public int getNumberCompletedMocks() {
        return numberCompletedMocks;
    }

    public void setNumberCompletedMocks(int numberCompletedMocks) {
        this.numberCompletedMocks = numberCompletedMocks;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public Set<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(Set<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public Set<MockRegistration> getMockInterviews() {
        return mockInterviews;
    }

    public void setMockInterviews(Set<MockRegistration> mockInterviews) {
        this.mockInterviews = mockInterviews;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Feedback> getReceivedFeedback() {
        return receivedFeedback;
    }

    public void setReceivedFeedback(List<Feedback> receivedFeedback) {
        this.receivedFeedback = receivedFeedback;
    }

    public List<Feedback> getSentFeedback() {
        return sentFeedback;
    }

    public void setSentFeedback(List<Feedback> sentFeedback) {
        this.sentFeedback = sentFeedback;
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
        UserAccount that = (UserAccount) o;
        return Objects.equals(user, that.user) && Objects.equals(profilePictureUrl, that.profilePictureUrl) && Objects.equals(numberCompletedMocks, that.numberCompletedMocks) && proficiencyLevel == that.proficiencyLevel && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, profilePictureUrl, numberCompletedMocks, proficiencyLevel, createdAt);
    }
}
