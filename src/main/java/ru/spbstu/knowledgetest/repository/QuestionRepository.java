package ru.spbstu.knowledgetest.repository;

import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.Question;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

@Repository
public interface QuestionRepository extends BaseRepository<Question, String> {
}
