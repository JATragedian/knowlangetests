package ru.spbstu.knowledgetest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.enums.UserRole;
import ru.spbstu.knowledgetest.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthUserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthUserService authUserService;

    @BeforeEach
    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setRole(UserRole.ADMINISTRATOR);
        user.setPassword("password");

        userRepository.save(user);

        UserDetails userDetails = authUserService.loadUserByUsername("email");

        assertEquals("email", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
    }
}