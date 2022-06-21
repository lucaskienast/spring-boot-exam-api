package com.kienast.examapplication.repository;

import com.kienast.examapplication.model.GivenAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GivenAnswerRepository extends JpaRepository<GivenAnswer, Long> {
}
