package ru.spbstu.knowledgetest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.enums.UserRole;
import ru.spbstu.knowledgetest.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void findAll() {
        createUser();
        createUser();

        Page<User> users = userService.findAll(PageRequest.of(0, 25));

        assertEquals(2, users.getTotalElements());
        assertEquals("name", users.getContent().get(0).getName());
        assertEquals("email", users.getContent().get(1).getEmail());
    }

    @Test
    void findById() {
        User user = createUser();
        User fetchedUser = userService.findById(user.getId());

        assertNotNull(fetchedUser);
    }

    @Test
    void save() {
        User user = new User();
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setRole(UserRole.STUDENT);
        user.setPassword("pass");

        userService.save(user);

        assertEquals(1, userRepository.count());
    }

    @Test
    void update() {
        User user = createUser();

        User updatedUser = new User();
        updatedUser.setName("new_name");
        updatedUser.setSurname("surname");
        updatedUser.setEmail("email");
        updatedUser.setRole(UserRole.STUDENT);
        updatedUser.setPassword("pass");

        updatedUser.setId(user.getId());
        User fetchedUser = userService.update(updatedUser);

        assertEquals(1, userRepository.count());
        assertEquals("new_name", fetchedUser.getName());
    }

    private User createUser() {
        User user = new User();
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setRole(UserRole.STUDENT);
        user.setPassword("pass");

        return userRepository.save(user);
    }
}