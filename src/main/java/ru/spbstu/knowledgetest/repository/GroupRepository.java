package ru.spbstu.knowledgetest.repository;

import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.Group;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

@Repository
public interface GroupRepository extends BaseRepository<Group, String> {
}
