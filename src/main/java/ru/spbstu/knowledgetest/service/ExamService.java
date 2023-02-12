package ru.spbstu.knowledgetest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.spbstu.knowledgetest.domain.Exam;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.domain.Question;
import ru.spbstu.knowledgetest.dto.QuestionStatistics;
import ru.spbstu.knowledgetest.enums.BloomLevel;
import ru.spbstu.knowledgetest.enums.UserRole;
import ru.spbstu.knowledgetest.repository.ExamInstanceRepository;
import ru.spbstu.knowledgetest.repository.ExamRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExamService {

    ExamRepository examRepository;
    ExamInstanceRepository examInstanceRepository;

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

    public List<QuestionStatistics> getQuestionLevels(String examId) {
        Exam exam = findById(examId);
        return getQuestionStatistics(exam.getQuestions());
    }

    public List<QuestionStatistics> getQuestionLevels(List<Question> questions) {
        return getQuestionStatistics(questions);
    }

    private List<QuestionStatistics> getQuestionStatistics(List<Question> questions) {
        Map<BloomLevel, Long> levels = questions.stream()
                .collect(Collectors.groupingBy(Question::getLevel, Collectors.counting()));

        for (BloomLevel level : BloomLevel.values()) {
            levels.putIfAbsent(level, 0L);
        }

        List<QuestionStatistics> statistics = new ArrayList<>();
        for (BloomLevel level : levels.keySet()) {
            double intLevelSize = levels.get(level).intValue();
            double totalSize = questions.size();

            statistics.add(new QuestionStatistics((intLevelSize / totalSize) * 100,
                    StringUtils.capitalize(level.name().toLowerCase())));
        }

        return statistics;
    }

    public Page<Exam> findAllByOwnerId(String ownerId, Pageable pageable) {
        return examRepository.findAllByOwnerId(ownerId, pageable);
    }

    public List<Exam> findAllByStudentId(String studentId, Pageable pageable) {
        Set<String> examInstanceIds = examInstanceRepository.findAllByStudentId(studentId).stream()
                .collect(Collectors.groupingBy(ExamInstance::getExamId)).keySet();

        return examInstanceIds.stream()
                .map(examRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }
}
