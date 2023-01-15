package ru.spbstu.knowledgetest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.spbstu.knowledgetest.domain.Exam;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.enums.ExamStatus;
import ru.spbstu.knowledgetest.repository.ExamInstanceRepository;
import ru.spbstu.knowledgetest.util.ResultCalculationUtil;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExamInstanceService {

    ExamInstanceRepository examInstanceRepository;
    ExamService examService;
    AnswerService answerService;

    public ExamInstance startExam(String id) {
        ExamInstance examInstance = findById(id);

        if (!ExamStatus.AVAILABLE.equals(examInstance.getExamStatus())) {
            throw new IllegalArgumentException("Exam cannot be started anymore");
        }

        examInstance.setExamStatus(ExamStatus.STARTED);
        examInstance.setStartedTime(LocalDateTime.now());
        return examInstanceRepository.save(examInstance);
    }

    public ExamInstance completeExam(String id) {
        ExamInstance examInstance = findById(id);
        int score = ResultCalculationUtil.getScore(
                examService.findById(examInstance.getExamId()).getQuestions(),
                answerService.findAllByExamInstanceId(examInstance.getId())
        );

        examInstance.setScore(score);
        examInstance.setExamStatus(ExamStatus.COMPLETE);
        return examInstance;
    }

    public Page<ExamInstance> findAllByStudentId(String studentId, Pageable pageable) {
        return examInstanceRepository.findAllByStudentId(studentId, pageable);
    }

    public Page<ExamInstance> findAllByStudentIdAndExamStatus(String studentId, String examStatus, Pageable pageable) {
        ExamStatus examStatusEnumValue;
        try {
            examStatusEnumValue = ExamStatus.valueOf(examStatus);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Unknown exam status: " + examStatus);
        }
        return examInstanceRepository.findAllByStudentIdAndExamStatus(studentId, examStatusEnumValue, pageable);
    }

    public Page<ExamInstance> findAll(Pageable pageable) {
        return examInstanceRepository.findAll(pageable);
    }

    public ExamInstance findById(String id) {
        return examInstanceRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No exam instance found with ID: " + id));
    }

    public ExamInstance save(ExamInstance examInstance) {
        Exam exam = examService.findById(examInstance.getExamId());
        if (exam == null) {
            throw new IllegalArgumentException("Exam does not exists: " + examInstance.getExamId());
        }
        return examInstanceRepository.save(examInstance);
    }

    public ExamInstance update(ExamInstance examInstance) {
        findById(examInstance.getId());
        return save(examInstance);
    }
}
