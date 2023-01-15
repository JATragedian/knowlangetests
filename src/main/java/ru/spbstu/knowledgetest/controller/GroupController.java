package ru.spbstu.knowledgetest.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.knowledgetest.domain.Group;
import ru.spbstu.knowledgetest.service.GroupService;
import ru.spbstu.knowledgetest.util.PaginationUtil;

@RestController
@RequestMapping("api/v1/groups")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupController {
    
    GroupService groupService;
    PaginationUtil paginationUtil;

    @GetMapping
    public Page<Group> findAll(
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size
    ) {
        return groupService.findAll(paginationUtil.of(page, size));
    }

    @GetMapping("/{id}")
    public Group findById(@PathVariable("id") String id) {
        return groupService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'TEACHER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Group save(@RequestBody Group group) {
        return groupService.save(group);
    }

    @PutMapping
    @ResponseStatus
    public Group update(@RequestBody Group group) {
        return groupService.update(group);
    }
}
