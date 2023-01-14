package ru.spbstu.knowledgetest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.spbstu.knowledgetest.domain.Group;
import ru.spbstu.knowledgetest.repository.GroupRepository;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupService {

    GroupRepository groupRepository;

    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    public Group findById(String id) {
        return groupRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No group found with ID: " + id));
    }

    public Group save(Group exam) {
        return groupRepository.save(exam);
    }

    public Group update(Group exam) {
        findById(exam.getId());
        return groupRepository.save(exam);
    }
}
