package ru.spbstu.knowledgetest.util;

import org.junit.jupiter.api.Test;
import ru.spbstu.knowledgetest.domain.Answer;
import ru.spbstu.knowledgetest.domain.Question;
import ru.spbstu.knowledgetest.enums.BloomLevel;
import ru.spbstu.knowledgetest.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultCalculationUtilTest {

    @Test
    void getScore() {
        Question question1 = new Question();
        question1.setContent("");
        question1.setType(QuestionType.SINGLE);
        question1.setLevel(BloomLevel.KNOWLEDGE);
        question1.setWeight(4);
        question1.setCorrectAnswers(List.of("true"));

        Question question2 = new Question();
        question1.setContent("");
        question1.setType( QuestionType.SINGLE);
        question1.setLevel(BloomLevel.KNOWLEDGE);
        question1.setWeight(7);
        question1.setCorrectAnswers(List.of("true"));

        Question question3 = new Question();
        question1.setContent("");
        question1.setType( QuestionType.SINGLE);
        question1.setLevel(BloomLevel.ANALYSIS);
        question1.setWeight(2);
        question1.setCorrectAnswers(List.of("true"));

        question1.setId("1");
        question2.setId("2");
        question3.setId("3");

        List<Question> questions = List.of(question1, question2, question3);

        Answer answer1 = new Answer();
        answer1.setQuestionId("1");
        answer1.setExamInstanceId(null);
        answer1.setContent("true");

        Answer answer2 = new Answer();
        answer2.setQuestionId("1");
        answer2.setExamInstanceId(null);
        answer2.setContent("true");

        Answer answer3 = new Answer();
        answer3.setQuestionId("1");
        answer3.setExamInstanceId(null);
        answer3.setContent("true");

        List<Answer> answers = List.of(answer1, answer2, answer3);

        int score = ResultCalculationUtil.getScore(questions, answers);

        assertEquals(84, score);
    }

    @Test
    void getScoreWithNoAnswers() {
        Question question1 = new Question();
        question1.setContent("");
        question1.setType(QuestionType.SINGLE);
        question1.setLevel(BloomLevel.KNOWLEDGE);
        question1.setWeight(4);
        question1.setCorrectAnswers(List.of("true"));

        Question question2 = new Question();
        question1.setContent("");
        question1.setType( QuestionType.SINGLE);
        question1.setLevel(BloomLevel.ANALYSIS);
        question1.setWeight(7);
        question1.setCorrectAnswers(List.of("true"));

        Question question3 = new Question();
        question1.setContent("");
        question1.setType( QuestionType.SINGLE);
        question1.setLevel(BloomLevel.APPLICATION);
        question1.setWeight(7);
        question1.setCorrectAnswers(List.of("true"));

        question1.setId("1");
        question2.setId("2");
        question3.setId("3");

        List<Question> questions = List.of(question1, question2, question3);

        int score = ResultCalculationUtil.getScore(questions, new ArrayList<>());

        assertEquals(0, score);
    }
}