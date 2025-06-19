package com.quiz.QuizApplication.Controller;



import com.quiz.QuizApplication.Entity.Question;
import com.quiz.QuizApplication.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allquestion")
    public ResponseEntity<List<Question>> getAllquestions() {
        return QuestionService.getAllquestion();
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> getAllquestionbycategory(@PathVariable("cat") String category){
        return QuestionService.getAllquestionbycategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addquestion(@RequestBody Question que){
        return questionService.addquestion(que);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Question> updateAuthor(@PathVariable int id,@RequestBody Question que){
        Question existQuestion=QuestionService.getQuestionById(id);
        if(existQuestion==null)
            return ResponseEntity.notFound().build();
        que.setQid(id);
        questionService.saveOrUpdate(que);
        return new ResponseEntity<>(que, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletequestion(@PathVariable int id){
        Question question1=QuestionService.getQuestionById(id);
        if(question1==null)
            return ResponseEntity.notFound().build();
        questionService.deleteQuestionById(id);
        return ResponseEntity.noContent().build();
    }


}