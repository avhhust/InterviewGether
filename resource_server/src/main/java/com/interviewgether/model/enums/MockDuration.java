package com.interviewgether.model.enums;

public enum MockDuration {
    ONE_HOUR(60),
    ONE_HOUR_AND_HALF(90);

    private final int durationMinutes;

    MockDuration(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
