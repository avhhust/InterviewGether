package com.interviewgether.repository;

import com.interviewgether.model.UserAccount;
import com.interviewgether.model.embeddable.SolvedProblems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

}
