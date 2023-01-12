package ru.spbstu.knowledgetest.repository;

import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

    User findByEmail(String email);
}
