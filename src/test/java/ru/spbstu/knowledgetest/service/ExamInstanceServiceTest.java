package ru.spbstu.knowledgetest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.repository.ExamInstanceRepository;
import ru.spbstu.knowledgetest.util.PaginationUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExamInstanceServiceTest {

    @Autowired
    private ExamInstanceService examInstanceService;
    @Autowired
    private PaginationUtil paginationUtil;
    @Autowired
    private ExamInstanceRepository examInstanceRepository;

    @Test
    void save() {
        String examId = "examId2";
        String studentId = "studentId342";

        ExamInstance savedExam = examInstanceService.save(new ExamInstance(examId, studentId));

        ExamInstance exam = examInstanceService.findById(savedExam.getId());
        assertEquals(studentId, exam.getStudentId());
        assertEquals(examId, exam.getExamId());

        examInstanceRepository.delete(savedExam);
    }

    @Test
    void findByStudentId() {

        String examId = "examId2";
        String studentId = "studentId5213";

        examInstanceService.save(new ExamInstance(examId, studentId));
        examInstanceService.save(new ExamInstance(examId, studentId));

        Page<ExamInstance> exams = examInstanceService.findAllByStudentId(studentId, paginationUtil.of(0, 25));

        assertEquals(2, exams.getTotalElements());

        examInstanceRepository.deleteAll(exams);
    }
}