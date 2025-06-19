package com.quiz.QuizApplication.Service;


import com.quiz.QuizApplication.Repository.QuestionRepository;
import com.quiz.QuizApplication.Entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    static QuestionRepository questionRepository;

    public static ResponseEntity<List<Question>> getAllquestion() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity<List<Question>> getAllquestionbycategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findBycategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addquestion(Question que) {
        questionRepository.save(que);
        return new ResponseEntity<>("SUCCESS INSERTED",HttpStatus.CREATED);
    }

    public static Question getQuestionById(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    public void deleteQuestionById(int id) {
        questionRepository.findById(id).orElse(null);
        questionRepository.deleteById(id);
    }


    public void saveOrUpdate(Question que) {
        questionRepository.save(que);
    }
}