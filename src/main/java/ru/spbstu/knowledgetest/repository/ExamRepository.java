package ru.spbstu.knowledgetest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.Exam;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

@Repository
public interface ExamRepository extends BaseRepository<Exam, String> {

    Page<Exam> findAllByOwnerId(String ownerId, Pageable pageable);
}
