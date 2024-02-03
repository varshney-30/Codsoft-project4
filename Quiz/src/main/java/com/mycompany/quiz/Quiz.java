/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quiz;

/**
 *
 * @author Aparna
 */
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private Question[] questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private TimerTask timerTask;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void start() {
        timer = new Timer();
        scheduleNextQuestion();
    }

    private void scheduleNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];
            System.out.println(currentQuestion.getQuestion());
            for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + currentQuestion.getOptions()[i]);
            }

            timerTask = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    handleIncorrectAnswer();
                }
            };
            timer.schedule(timerTask, 5000); // 5 seconds per question
        } else {
            endQuiz();
        }
    }

    private void handleIncorrectAnswer() {
        System.out.println("Incorrect. The correct answer is: " + questions[currentQuestionIndex].getCorrectOption());
        currentQuestionIndex++;
        scheduleNextQuestion();
    }

    private void endQuiz() {
        timer.cancel();
        System.out.println("Quiz finished!");
        System.out.println("Your score is: " + score);
    }

    public static void main(String[] args) {
        Question[] questions = {
                new Question("What is the capital of France?", new String[]{"Paris", "Berlin", "Madrid"}, 1),
                new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter"}, 3),
                new Question("What is the chemical symbol for water?", new String[]{"H2O", "CO2", "NH3"}, 1),
                new Question("Which planet is closest to the Sun?", new String[]{"Mercury", "Venus", "Earth"}, 1),
                new Question("What is the largest ocean in the world?", new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean"}, 1),
                new Question("Which is the largest animal on Earth?", new String[]{"Elephant", "Whale", "Rhino"}, 2),
                new Question("Which is the largest country in the world by area?", new String[]{"Russia", "Canada", "China"}, 1),
                new Question("Which is the largest island in the world?", new String[]{"Greenland", "Madagascar", "New Guinea"}, 1),
                new Question("Which is the largest freshwater lake in the world?", new String[]{"Lake Superior", "Lake Huron", "Lake Michigan"}, 1),
                new Question("Which is the largest mountain in the world?", new String[]{"Mount Everest", "K2", "Kangchenjunga"}, 1),
                new Question("Which is the largest star in the universe?", new String[]{"Sun", "Alpha Centauri", "Betelgeuse"}, 3)
        };
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctOptionNumber;

    public Question(String question, String[] options, int correctOptionNumber) {
        this.question = question;
        this.options = options;
        this.correctOptionNumber = correctOptionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionNumber() {
        return correctOptionNumber;
    }

    public String getCorrectOption() {
        return options[correctOptionNumber - 1];
    }
}
