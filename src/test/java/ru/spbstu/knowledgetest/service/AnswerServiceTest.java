package ru.spbstu.knowledgetest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.spbstu.knowledgetest.domain.Answer;
import ru.spbstu.knowledgetest.repository.AnswerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerServiceTest {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    @AfterEach
    void clean() {
        answerRepository.deleteAll();
    }

    @Test
    void findAll() {
        createAnswer();
        createAnswer();

        Page<Answer> users = answerService.findAll(PageRequest.of(0, 25));

        assertEquals(2, users.getTotalElements());
        assertEquals("questionId1", users.getContent().get(0).getQuestionId());
        assertEquals("content", users.getContent().get(1).getContent());
    }

    @Test
    void findById() {
        Answer answer = createAnswer();
        Answer fetchedAnswer = answerService.findById(answer.getId());

        assertNotNull(fetchedAnswer);
    }

    @Test
    void save() {
        Answer answer = new Answer();
        answer.setQuestionId("questionId1");
        answer.setExamInstanceId("examInstanceId1");
        answer.setContent("content_new");

        answerRepository.save(answer);

        assertEquals(1, answerRepository.count());
    }

    @Test
    void update() {
        Answer answer = createAnswer();
        Answer updatedAnswer = new Answer();
        updatedAnswer.setQuestionId("questionId1");
        updatedAnswer.setExamInstanceId("examInstanceId1");
        updatedAnswer.setContent("content_new");
        updatedAnswer.setId(answer.getId());

        Answer fetchedAnswer = answerService.update(updatedAnswer);

        assertEquals(1, answerRepository.count());
        assertEquals("content_new", fetchedAnswer.getContent());
    }

    private Answer createAnswer() {
        Answer answer = new Answer();
        answer.setQuestionId("questionId1");
        answer.setExamInstanceId("examInstanceId1");
        answer.setContent("content");

        return answerRepository.save(answer);
    }
}