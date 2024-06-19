package com.interviewgether.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class RatingAspect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "rating_aspect_name", length = 50)
    private String ratingAspectName;

    public RatingAspect() {
    }

    public RatingAspect(String ratingAspectName) {
        this.ratingAspectName = ratingAspectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRatingAspectName() {
        return ratingAspectName;
    }

    public void setRatingAspectName(String ratingAspectName) {
        this.ratingAspectName = ratingAspectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingAspect that = (RatingAspect) o;
        return Objects.equals(ratingAspectName, that.ratingAspectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingAspectName);
    }
}
