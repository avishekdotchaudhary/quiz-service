package com.iquiz.quiz.service;

import com.iquiz.quiz.dao.QuizDao;
import com.iquiz.quiz.feing.QuizInterface;
import com.iquiz.quiz.model.QuestionWrapper;
import com.iquiz.quiz.model.Quiz;
import com.iquiz.quiz.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

//    @Autowired
//    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
//        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
//        quizDao.save(
//                Quiz.builder()
//                        .title(title)
//                        .questions(questions)
//                        .build()
//                );

        List<Integer> questions = quizInterface.getQuestionForQuiZ(category, numQ).getBody();

        quizDao.save(
            Quiz.builder()
                .title(title)
                .questionIds(questions)
                .build()
        );

//        List<Integer> questions = // call generate url - RestTemplate http://localhost:8080/question/generate
        return ResponseEntity.ok("Quiz created successfully");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quizById = quizDao.findById(id).get();
        List<Integer> questionIds = quizById.getQuestionIds();


//        List<QuestionWrapper> questionForUser = new ArrayList<>();

//        for(Question question : questionFromDB){
//            QuestionWrapper qw = new QuestionWrapper(
//                    question.getId(),
//                    question.getQuestionTitle(),
//                    question.getOption1(),
//                    question.getOption2(),
//                    question.getOption3(),
//                    question.getOption4()
//            );
//            questionForUser.add(qw);
//        }
        return quizInterface.getQuestionsById(questionIds);

    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
//        Quiz quizById = quizDao.findById(id).get();
//        List<Question> questions = quizById.getQuestions();
//        int right = 0;
//        int i = 0;
//        for(Response response : responses){
//            if (response.getResponse().equals(questions.get(i).getCorrectAnswer())) {
//                right++;
//            }
//            i++;
//        }

        return quizInterface.getScore(responses);
    }
}
