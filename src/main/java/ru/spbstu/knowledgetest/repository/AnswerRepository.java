package ru.spbstu.knowledgetest.repository;

import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.Answer;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

import java.util.List;

@Repository
public interface AnswerRepository extends BaseRepository<Answer, String> {
    List<Answer> findAllByExamInstanceId(String examInstanceId);
}
