package com.iquiz.quiz.controller;

import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer numberOfQuestions;
    String title;
}
