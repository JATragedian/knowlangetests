package ru.spbstu.knowledgetest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.enums.ExamStatus;
import ru.spbstu.knowledgetest.repository.ExamInstanceRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class ExamInstanceService {

    ExamInstanceRepository examInstanceRepository;

    public Page<ExamInstance> findAll(Pageable pageable) {
        return examInstanceRepository.findAll(pageable);
    }

    public ExamInstance findById(String id) {
        return examInstanceRepository.findById(id).orElseThrow();
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

    public ExamInstance save(ExamInstance examInstance) {
        return examInstanceRepository.save(examInstance);
    }

    public ExamInstance update(ExamInstance examInstance) {
        Optional<ExamInstance> exam = examInstanceRepository.findById(examInstance.getId());
        if (exam.isPresent()) {
            return examInstanceRepository.save(examInstance);
        } else {
            throw new NoSuchElementException();
        }
    }
}
