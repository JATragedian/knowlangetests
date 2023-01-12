package ru.spbstu.knowledgetest.repository.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends MongoRepository<T, ID> {

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);
}
