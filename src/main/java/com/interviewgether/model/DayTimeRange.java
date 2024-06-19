package com.interviewgether.model;

import com.interviewgether.model.embeddable.TimeRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class DayTimeRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private OffsetDateTime date;

    @ElementCollection
    @CollectionTable(
            name = "time_ranges",
            joinColumns = @JoinColumn(name = "date")
    )
    private Set<TimeRange> timeRanges;

    public DayTimeRange() {
    }

    public DayTimeRange(Long id, OffsetDateTime date, Set<TimeRange> timeRanges) {
        this.id = id;
        this.date = date;
        this.timeRanges = timeRanges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public Set<TimeRange> getTimeRanges() {
        return timeRanges;
    }

    public void setTimeRanges(Set<TimeRange> timeRanges) {
        this.timeRanges = timeRanges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayTimeRange that = (DayTimeRange) o;
        return Objects.equals(date, that.date) && Objects.equals(timeRanges, that.timeRanges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, timeRanges);
    }
}
