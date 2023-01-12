package ru.spbstu.knowledgetest.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.knowledgetest.domain.ExamInstance;
import ru.spbstu.knowledgetest.enums.ExamStatus;
import ru.spbstu.knowledgetest.service.ExamInstanceService;
import ru.spbstu.knowledgetest.util.PaginationUtil;

@RestController
@RequestMapping("api/v1/exam-instances")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExamInstanceController {

    ExamInstanceService examInstanceService;
    PaginationUtil paginationUtil;

    @GetMapping
    public Page<ExamInstance> findAll(
            @RequestParam(required = false) String studentId,  @RequestParam(required = false) String examStatus,
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size
    ) {
        if (StringUtils.isNotBlank(studentId)) {
            if (StringUtils.isNotBlank(examStatus)) {
                return examInstanceService.findAllByStudentIdAndExamStatus(
                        studentId, examStatus, paginationUtil.of(page, size)
                );
            }
            return examInstanceService.findAllByStudentId(studentId, paginationUtil.of(page, size));
        }
        return examInstanceService.findAll(paginationUtil.of(page, size));
    }

    @GetMapping("/{id}")
    public ExamInstance findById(@PathVariable("id") String id) {
        return examInstanceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExamInstance save(@RequestBody ExamInstance examInstance) {
        return examInstanceService.save(examInstance);
    }

    @PutMapping
    @ResponseStatus
    public ExamInstance update(@RequestBody ExamInstance examInstance) {
        return examInstanceService.update(examInstance);
    }
}
