package ru.spbstu.knowledgetest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.spbstu.knowledgetest.domain.Group;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.enums.UserRole;
import ru.spbstu.knowledgetest.repository.GroupRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    @AfterEach
    void clean() {
        groupRepository.deleteAll();
    }

    @Test
    void findAll() {
        createGroup();
        createGroup();

        Page<Group> users = groupService.findAll(PageRequest.of(0, 25));

        assertEquals(2, users.getTotalElements());
        assertEquals("group", users.getContent().get(0).getName());
        assertEquals(List.of("1", "2"), users.getContent().get(1).getUserIds());
    }

    @Test
    void findById() {
        Group group = createGroup();
        Group fetchedGroup = groupService.findById(group.getId());

        assertNotNull(fetchedGroup);
    }

    @Test
    void save() {
        Group group = new Group();
        group.setName("group");
        group.setUserIds(List.of("1", "2"));

        groupRepository.save(group);

        assertEquals(1, groupRepository.count());
    }

    @Test
    void update() {
        Group group = createGroup();
        Group updatedGroup = new Group();
        updatedGroup.setName("new_name");
        updatedGroup.setUserIds(List.of("1"));
        updatedGroup.setId(group.getId());
        Group fetchedGroup = groupService.update(updatedGroup);

        assertEquals(1, groupRepository.count());
        assertEquals("new_name", fetchedGroup.getName());
    }

    private Group createGroup() {
        Group group = new Group();
        group.setName("group");
        group.setUserIds(List.of("1", "2"));

        return groupRepository.save(group);
    }
}