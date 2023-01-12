package ru.spbstu.knowledgetest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.enums.ExamStatus;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

@Repository
public interface ExamInstanceRepository extends BaseRepository<ExamInstance, String> {

    Page<ExamInstance> findAllByStudentId(String studentId, Pageable pageable);

    Page<ExamInstance> findAllByStudentIdAndExamStatus(String studentId, ExamStatus examStatus, Pageable pageable);
}
