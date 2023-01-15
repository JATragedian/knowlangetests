package ru.spbstu.knowledgetest.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.knowledgetest.domain.Exam;
import ru.spbstu.knowledgetest.enums.BloomLevel;
import ru.spbstu.knowledgetest.service.ExamService;
import ru.spbstu.knowledgetest.util.PaginationUtil;

import java.util.Map;

@RestController
@RequestMapping("api/v1/exams")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExamController {

    ExamService examService;
    PaginationUtil paginationUtil;

    @GetMapping
    public Page<Exam> findAll(
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size
    ) {
        return examService.findAll(paginationUtil.of(page, size));
    }

    @GetMapping("/{id}")
    public Exam findById(@PathVariable("id") String id) {
        return examService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'TEACHER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Exam save(@RequestBody Exam exam) {
        return examService.save(exam);
    }

    @PutMapping
    @ResponseStatus
    public Exam update(@RequestBody Exam exam) {
        return examService.update(exam);
    }

    @GetMapping("/{id}/questions/levels")
    public Map<BloomLevel, Long> getQuestionLevels(@PathVariable("id") String id) {
        return examService.getQuestionLevels(id);
    }
}
