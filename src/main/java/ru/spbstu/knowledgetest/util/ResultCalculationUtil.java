package ru.spbstu.knowledgetest.util;

import lombok.experimental.UtilityClass;
import org.springframework.transaction.annotation.Transactional;
import ru.spbstu.knowledgetest.domain.Answer;
import ru.spbstu.knowledgetest.domain.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ResultCalculationUtil {

    public int getScore(List<Question> questions, List<Answer> answers) {
        int points = 0;
        int maxPoints = 0;

        Map<String, Answer> answersMap = getAnswersMapByQuestionId(answers);

        for (Question question : questions) {
            int weight = question.getWeight();
            maxPoints += weight;
            Answer answer = answersMap.get(question.getId());
            if (answer != null && isRightAnswer(answer, question)) {
                points += weight;
            }
        }

        double dPoints = points;
        double dMaxPoints = maxPoints;

        return (int) Math.floor((dPoints/dMaxPoints) * 100);
    }

    private boolean isRightAnswer(Answer answer, Question question) {
        for (String rightAnswer : question.getCorrectAnswers()) {
            if (answer.getContent().equals(rightAnswer)) {
                return true;
            }
        }
        return false;
    }

    private Map<String, Answer> getAnswersMapByQuestionId(List<Answer> answers) {
        Map<String, Answer> map = new HashMap<>();
        for (Answer answer : answers) {
            map.put(answer.getQuestionId(), answer);
        }
        return map;
    }
}
