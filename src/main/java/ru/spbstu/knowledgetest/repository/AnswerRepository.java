package ru.spbstu.knowledgetest.repository;

import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.Answer;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

@Repository
public interface AnswerRepository extends BaseRepository<Answer, String> {
}
