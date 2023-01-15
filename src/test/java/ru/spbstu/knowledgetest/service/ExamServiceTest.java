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
        examRepository.save(new Exam(
                "exam",
                "ownerId",
                "description",
                60,
                List.of(new Question(
                        "content",
                        QuestionType.SINGLE,
                        BloomLevel.KNOWLEDGE,
                        5,
                        List.of("true")
                )))
        );

        assertEquals(1, examRepository.count());
    }

    @Test
    void update() {
        Exam exam = createExam();
        Exam updatedExam = new Exam(
                "new_name",
                "ownerId",
                "description",
                60,
                List.of(new Question(
                        "content",
                        QuestionType.SINGLE,
                        BloomLevel.KNOWLEDGE,
                        5,
                        List.of("true")
                ))
        );
        updatedExam.setId(exam.getId());
        Exam fetchedExam = examService.update(updatedExam);

        assertEquals(1, examRepository.count());
        assertEquals("new_name", fetchedExam.getName());
    }

    @Test
    void getQuestionLevels() {
        Exam exam = createExam();
        Map<BloomLevel, Long> questionLevels = examService.getQuestionLevels(exam.getId());

        assertEquals(1, questionLevels.get(BloomLevel.KNOWLEDGE));
        assertEquals(1, questionLevels.get(BloomLevel.ANALYSIS));
        assertEquals(0, questionLevels.get(BloomLevel.SYNTHESIS));
    }

    private Exam createExam() {
        return examRepository.save(new Exam(
                "exam",
                "ownerId",
                "description",
                60,
                List.of(new Question(
                                "content",
                                QuestionType.SINGLE,
                                BloomLevel.KNOWLEDGE,
                                5,
                                List.of("true")
                        ),
                        new Question(
                                "content",
                                QuestionType.SINGLE,
                                BloomLevel.ANALYSIS,
                                7,
                                List.of("true")
                        )
                )
        ));
    }
}