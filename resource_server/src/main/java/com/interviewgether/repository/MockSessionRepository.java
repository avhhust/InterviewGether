package com.interviewgether.repository;

import com.interviewgether.model.MockSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockSessionRepository extends JpaRepository<MockSession, Long> {

}
