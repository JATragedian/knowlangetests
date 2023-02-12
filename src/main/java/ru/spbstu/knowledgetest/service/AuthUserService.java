package ru.spbstu.knowledgetest.service;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.dto.UserInfo;
import ru.spbstu.knowledgetest.enums.UserRole;
import ru.spbstu.knowledgetest.repository.UserRepository;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    private void setupAdminUser() {
        if (userRepository.count() == 0) {
            User defaultUser = new User();
            defaultUser.setName("Administrator");
            defaultUser.setSurname("System-account");
            defaultUser.setEmail("admin@admin.com");
            defaultUser.setRole(UserRole.ADMINISTRATOR);
            defaultUser.setPassword(new BCryptPasswordEncoder().encode("admin"));

            userRepository.save(defaultUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with such email not found: " + username);
        }

        return new UserInfo(user);
    }
}
