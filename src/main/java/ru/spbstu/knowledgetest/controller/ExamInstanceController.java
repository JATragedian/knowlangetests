package ru.spbstu.knowledgetest.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.knowledgetest.domain.ExamInstance;
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
            @RequestParam(required = false) String studentId,  @RequestParam(required = false) String examId,
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size
    ) {
        if (StringUtils.isNotBlank(studentId)) {
            if (StringUtils.isNotBlank(examId)) {
                return examInstanceService.findAllByStudentIdAndExamId(
                        studentId, examId, paginationUtil.of(page, size,
                                Sort.by(Sort.Direction.DESC, "startedTime"))
                );
            }
            return examInstanceService.findAllByStudentId(studentId, paginationUtil.of(page, size,
                    Sort.by(Sort.Direction.DESC, "startedTime")));
        }
        return examInstanceService.findAll(paginationUtil.of(page, size,
                Sort.by(Sort.Direction.DESC, "startedTime")));
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
    public ExamInstance update(@RequestBody ExamInstance examInstance) {
        return examInstanceService.update(examInstance);
    }

    @PutMapping("{id}/start")
    public ExamInstance startExam(@PathVariable("id") String id) {
        return examInstanceService.startExam(id);
    }

    @PutMapping("{id}/complete")
    public ExamInstance completeExam(@PathVariable("id") String id) {
        return examInstanceService.completeExam(id);
    }
}
