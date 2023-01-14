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
        Question question1 =
                new Question("", QuestionType.SINGLE, BloomLevel.KNOWLEDGE, 4, List.of("true"));
        Question question2 =
                new Question("", QuestionType.SINGLE, BloomLevel.ANALYSIS, 7, List.of("true"));
        Question question3 =
                new Question("", QuestionType.TEXT, BloomLevel.APPLICATION, 2, List.of("true"));

        question1.setId("1");
        question2.setId("2");
        question3.setId("3");

        List<Question> questions = List.of(question1, question2, question3);
        List<Answer> answers = List.of(
                new Answer("1", null, "true"),
                new Answer("2", null, "true"),
                new Answer("3", null, "false")
        );

        int score = ResultCalculationUtil.getScore(questions, answers);

        assertEquals(84, score);
    }

    @Test
    void getScoreWithNoAnswers() {
        Question question1 =
                new Question("", QuestionType.SINGLE, BloomLevel.KNOWLEDGE, 4, List.of("true"));
        Question question2 =
                new Question("", QuestionType.SINGLE, BloomLevel.ANALYSIS, 7, List.of("true"));
        Question question3 =
                new Question("", QuestionType.TEXT, BloomLevel.APPLICATION, 2, List.of("true"));

        question1.setId("1");
        question2.setId("2");
        question3.setId("3");

        List<Question> questions = List.of(question1, question2, question3);

        int score = ResultCalculationUtil.getScore(questions, new ArrayList<>());

        assertEquals(0, score);
    }
}