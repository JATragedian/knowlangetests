package ru.spbstu.knowledgetest.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.knowledgetest.domain.User;
import ru.spbstu.knowledgetest.dto.UserInfo;
import ru.spbstu.knowledgetest.service.UserService;
import ru.spbstu.knowledgetest.util.PaginationUtil;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserService userService;
    PaginationUtil paginationUtil;

    @GetMapping("/current")
    public User getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalArgumentException("User is not authenticated");
        }

        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        return userInfo.user();
    }

    @GetMapping
    public Page<User> findAll(
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String role
    ) {
        if (StringUtils.isNotBlank(role)) {
            return userService.findAllByRole(role, paginationUtil.of(page, size));
        }
        return userService.findAll(paginationUtil.of(page, size));
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    @ResponseStatus
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
