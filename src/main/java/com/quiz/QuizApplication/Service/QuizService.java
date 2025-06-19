package com.quiz.QuizApplication.Service;


import com.quiz.QuizApplication.Entity.Response;
import com.quiz.QuizApplication.Entity.Question;
import com.quiz.QuizApplication.Entity.QuestionWrapper;
import com.quiz.QuizApplication.Entity.Quiz;
import com.quiz.QuizApplication.Repository.QuestionRepository;
import com.quiz.QuizApplication.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    static QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;
    public ResponseEntity<String> createquiz(String category, int numQ, String title) {

        List<Question> questions=questionRepository.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz1=new Quiz();
        quiz1.setTitle(title);
        quiz1.setQuestions(questions);
        quizRepository.save(quiz1);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public static ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {
        Optional<Quiz> Quiz=quizRepository.findById(id);
        List<Question> questionFromDb=Quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser=new ArrayList<>();
        for(Question q:questionFromDb){
            QuestionWrapper qw=new QuestionWrapper(q.getQid(),q.getQuestiontitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz1=quizRepository.findById(id).get();
        List<Question> questions=quiz1.getQuestions();
        int right=0;
        int i=0;
        for(Response re:responses){
            if(re.getResponse().equals(questions.get(i).getRightanswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}