package ru.spbstu.knowledgetest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.enums.UserRole;
import ru.spbstu.knowledgetest.repository.UserRepository;

import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    UserRepository userRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No question found with ID: " + id));
    }

    public Page<User> findAllByRole(String role, Pageable pageable) {
        try {
            return userRepository.findAllByRole(UserRole.valueOf(role), pageable);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Role does not exists: " + role);
        }
    }

    public User save(User user) {
        User byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail != null) {
            throw new IllegalArgumentException("User with such email already exists");
        }

        String password = user.getPassword();
        if (StringUtils.isNotBlank(password)) {
            user.setPassword(new BCryptPasswordEncoder().encode(password));
        }
        return userRepository.save(user);
    }

    public User update(User user) {
        findById(user.getId());
        return userRepository.save(user);
    }
}
