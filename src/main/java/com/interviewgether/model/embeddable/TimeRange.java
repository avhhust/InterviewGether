package com.interviewgether.model.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Embeddable
public class TimeRange {

    @NotNull
    private OffsetTime startTime;
    @NotNull
    private OffsetTime endTime;

    public TimeRange() {
    }

    public TimeRange(OffsetTime startTime, OffsetTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OffsetTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetTime startTime) {
        this.startTime = startTime;
    }

    public OffsetTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetTime endTime) {
        this.endTime = endTime;
    }
}
