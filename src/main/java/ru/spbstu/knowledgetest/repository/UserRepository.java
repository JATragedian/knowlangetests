package ru.spbstu.knowledgetest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.enums.UserRole;
import ru.spbstu.knowledgetest.repository.base.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

    User findByEmail(String email);

    Page<User> findAllByRole(UserRole role, Pageable pageable);
}
