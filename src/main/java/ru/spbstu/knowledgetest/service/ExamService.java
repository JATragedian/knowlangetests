package ru.spbstu.knowledgetest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.spbstu.knowledgetest.domain.Exam;
import ru.spbstu.knowledgetest.domain.Question;
import ru.spbstu.knowledgetest.enums.BloomLevel;
import ru.spbstu.knowledgetest.repository.ExamRepository;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExamService {

    ExamRepository examRepository;

    public Page<Exam> findAll(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    public Exam findById(String id) {
        return examRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No exam found with ID: " + id));
    }

    public Exam save(Exam exam) {
        for (Question question : exam.getQuestions()) {
            if (question.getId() == null) {
                question.setId(new ObjectId().toString());
            }
        }
        return examRepository.save(exam);
    }

    public Exam update(Exam exam) {
        findById(exam.getId());
        return save(exam);
    }

    public Map<BloomLevel, Long> getQuestionLevels(String examId) {
        Exam exam = findById(examId);
        Map<BloomLevel, Long> levels = exam.getQuestions().stream()
                .collect(Collectors.groupingBy(Question::getLevel, Collectors.counting()));
        for (BloomLevel level : BloomLevel.values()) {
            levels.putIfAbsent(level, 0L);
        }
        return levels;
    }
}
