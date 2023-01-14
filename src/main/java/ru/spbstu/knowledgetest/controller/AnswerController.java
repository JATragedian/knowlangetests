package ru.spbstu.knowledgetest.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spbstu.knowledgetest.domain.Answer;
import ru.spbstu.knowledgetest.service.AnswerService;

@RestController
@RequestMapping("api/v1/answers")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AnswerController {

    AnswerService answerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Answer save(@RequestBody Answer answer) {
        return answerService.save(answer);
    }
}
