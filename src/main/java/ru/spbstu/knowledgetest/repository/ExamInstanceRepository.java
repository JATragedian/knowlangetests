package ru.spbstu.knowledgetest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.enums.ExamStatus;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

import java.util.List;

@Repository
public interface ExamInstanceRepository extends BaseRepository<ExamInstance, String> {

    List<ExamInstance> findAllByStudentId(String studentId);

    Page<ExamInstance> findAllByStudentId(String studentId, Pageable pageable);

    Page<ExamInstance> findAllByStudentIdAndExamId(String studentId, String examId, Pageable pageable);
}
