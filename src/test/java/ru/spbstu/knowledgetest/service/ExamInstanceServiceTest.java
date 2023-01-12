package ru.spbstu.knowledgetest.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.enums.ExamStatus;
import ru.spbstu.knowledgetest.repository.ExamInstanceRepository;
import ru.spbstu.knowledgetest.util.PaginationUtil;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExamInstanceServiceTest {

    @Autowired
    private ExamInstanceService examInstanceService;
    @Autowired
    private PaginationUtil paginationUtil;

    @Autowired
    private ExamInstanceRepository examInstanceRepository;

    @BeforeEach
    void clean() {
        examInstanceRepository.deleteAll();
    }

    @Test
    void save() {
        String examId = "examId2";
        String studentId = "studentId342";

        ExamInstance savedExam = examInstanceService.save(new ExamInstance(
                examId,
                studentId,
                ExamStatus.AVAILABLE,
                0,
                LocalDateTime.now()
        ));

        ExamInstance exam = examInstanceService.findById(savedExam.getId());
        assertEquals(studentId, exam.getStudentId());
        assertEquals(examId, exam.getExamId());
    }

    @Test
    void findByStudentId() {

        String examId = "examId2";
        String studentId = "studentId5213";

        examInstanceService.save(new ExamInstance(
                examId,
                studentId,
                ExamStatus.AVAILABLE,
                0,
                LocalDateTime.now()
        ));
        examInstanceService.save(new ExamInstance(
                examId,
                studentId,
                ExamStatus.AVAILABLE,
                0,
                LocalDateTime.now()
        ));

        Page<ExamInstance> exams = examInstanceService.findAllByStudentId(studentId, paginationUtil.of(0, 25));

        assertEquals(2, exams.getTotalElements());
    }
}