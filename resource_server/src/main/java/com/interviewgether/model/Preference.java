package com.interviewgether.model;

import com.interviewgether.model.enums.MockDuration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Preference {
    @Id
    private Long id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Application application;

    @NotNull(message = "Interview duration is required")
    @Enumerated(EnumType.STRING)
    @Column(length = 17)
    private MockDuration mockDuration = MockDuration.ONE_HOUR;

    @NotNull(message = "Total number of interviews is required")
    @Column(name = "total_number_interviews")
    private Integer totalNumberOfInterviews = 1;

    @Column(name = "interview_per_day")
    private Integer interviewsPerDay = 1;

    @Column(name = "additional_preferences")
    private String additionalPreferences;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "preference_id")
    private Set<DayTimeRange> dayTimeRanges = new HashSet<>();

    public Preference() {
    }

    public Preference(Application application, MockDuration mockDuration, Integer totalNumberOfInterviews, Integer interviewsPerDay, String additionalPreferences, Set<DayTimeRange> dayTimeRanges) {
        this.application = application;
        this.mockDuration = mockDuration;
        this.totalNumberOfInterviews = totalNumberOfInterviews;
        this.interviewsPerDay = interviewsPerDay;
        this.additionalPreferences = additionalPreferences;
        this.dayTimeRanges = dayTimeRanges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public MockDuration getMockDuration() {
        return mockDuration;
    }

    public void setMockDuration(MockDuration mockDuration) {
        this.mockDuration = mockDuration;
    }

    public Integer getTotalNumberOfInterviews() {
        return totalNumberOfInterviews;
    }

    public void setTotalNumberOfInterviews(Integer totalNumberOfInterviews) {
        this.totalNumberOfInterviews = totalNumberOfInterviews;
    }

    public Integer getInterviewsPerDay() {
        return interviewsPerDay;
    }

    public void setInterviewsPerDay(Integer interviewsPerDay) {
        this.interviewsPerDay = interviewsPerDay;
    }

    public String getAdditionalPreferences() {
        return additionalPreferences;
    }

    public void setAdditionalPreferences(String additionalPreferences) {
        this.additionalPreferences = additionalPreferences;
    }

    public Set<DayTimeRange> getDayTimeRanges() {
        return dayTimeRanges;
    }

    public void setDayTimeRanges(Set<DayTimeRange> dayTimeRanges) {
        this.dayTimeRanges = dayTimeRanges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return Objects.equals(application, that.application) && mockDuration == that.mockDuration && Objects.equals(totalNumberOfInterviews, that.totalNumberOfInterviews) && Objects.equals(interviewsPerDay, that.interviewsPerDay) && Objects.equals(additionalPreferences, that.additionalPreferences) && Objects.equals(dayTimeRanges, that.dayTimeRanges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, mockDuration, totalNumberOfInterviews, interviewsPerDay, additionalPreferences, dayTimeRanges);
    }
}
