package com.interviewgether.repository;

import com.interviewgether.model.RatingAspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingAspectRepository extends JpaRepository<RatingAspect, Long> {
}
