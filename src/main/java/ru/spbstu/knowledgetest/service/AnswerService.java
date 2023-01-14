package ru.spbstu.knowledgetest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.spbstu.knowledgetest.domain.Answer;
import ru.spbstu.knowledgetest.repository.AnswerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AnswerService  {

    AnswerRepository answerRepository;

    public Page<Answer> findAll(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public Answer findById(String id) {
        return answerRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No answer found with ID: " + id));
    }

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer update(Answer answer) {
        findById(answer.getId());
        return answerRepository.save(answer);
    }

    public List<Answer> findAllByExamInstanceId(String examInstanceId) {
        return answerRepository.findAllByExamInstanceId(examInstanceId);
    }
}
