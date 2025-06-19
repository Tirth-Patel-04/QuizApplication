package com.quiz.QuizApplication.Repository;

import com.quiz.QuizApplication.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
