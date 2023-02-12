package ru.spbstu.knowledgetest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.spbstu.knowledgetest.domain.Exam;
import ru.spbstu.knowledgetest.domain.Question;
import ru.spbstu.knowledgetest.dto.QuestionStatistics;
import ru.spbstu.knowledgetest.enums.BloomLevel;
import ru.spbstu.knowledgetest.enums.QuestionType;
import ru.spbstu.knowledgetest.repository.ExamRepository;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ExamServiceTest {

    @Autowired
    private ExamService examService;
    @Autowired
    private ExamRepository examRepository;

    @BeforeEach
    @AfterEach
    void clean() {
        examRepository.deleteAll();
    }

    @Test
    void findAll() {
        createExam();
        createExam();

        Page<Exam> users = examService.findAll(PageRequest.of(0, 25));

        assertEquals(2, users.getTotalElements());
        assertEquals("exam", users.getContent().get(0).getName());
        assertEquals("description", users.getContent().get(1).getDescription());
        assertEquals(60, users.getContent().get(1).getTimeLimit());
    }

    @Test
    void findById() {
        Exam exam = createExam();
        Exam fetchedExam = examService.findById(exam.getId());

        assertNotNull(fetchedExam);
    }

    @Test
    void save() {
        Question question = new Question();
        question.setContent("content");
        question.setType(QuestionType.SINGLE);
        question.setLevel(BloomLevel.KNOWLEDGE);
        question.setWeight(5);
        question.setCorrectAnswers(List.of("true"));

        examRepository.save(
                createExam("exam", List.of(
                        createQuestion(BloomLevel.KNOWLEDGE, 5,
                                List.of("true"))
                )));

        assertEquals(1, examRepository.count());
    }

    @Test
    void update() {
        Exam exam = createExam();

        Exam updatedExam = createExam("new_name",
                List.of(createQuestion(BloomLevel.KNOWLEDGE, 5,
                        List.of("true"))));

        updatedExam.setId(exam.getId());
        Exam fetchedExam = examService.update(updatedExam);

        assertEquals(1, examRepository.count());
        assertEquals("new_name", fetchedExam.getName());
    }

    @Test
    void getQuestionLevels() {
        Exam exam = createExam();
        List<QuestionStatistics> questionLevels = examService.getQuestionLevels(exam.getId());

        assertEquals(6, questionLevels.size());
        for (QuestionStatistics statistics : questionLevels) {
            if (statistics.getLabel().equals("Knowledge")) {
                assertEquals(50.0, statistics.getY());
            }
            if (statistics.getLabel().equals("Analysis")) {
                assertEquals(50.0, statistics.getY());
            }
            if (statistics.getLabel().equals("Synthesis")) {
                assertEquals(0.0, statistics.getY());
            }
        }
    }

    private Exam createExam(String name, List<Question> questions) {
        Exam exam = new Exam();
        exam.setName(name);
        exam.setOwnerId("ownerId");
        exam.setDescription("description");
        exam.setTimeLimit(60);
        exam.setQuestions(questions);
        return exam;
    }

    private Question createQuestion(
            BloomLevel level, int weight, List<String> correctAnswers
    ) {
        Question question = new Question();
        question.setContent("content");
        question.setType(QuestionType.SINGLE);
        question.setLevel(level);
        question.setWeight(weight);
        question.setCorrectAnswers(correctAnswers);
        return question;
    }

    private Exam createExam() {
        return examRepository.save(createExam(
                "exam",
                List.of(
                        createQuestion(
                                BloomLevel.KNOWLEDGE,
                                5,
                                List.of("true")
                        ),
                        createQuestion(
                                BloomLevel.ANALYSIS,
                                7,
                                List.of("true")
                        )
                )
        ));
    }
}