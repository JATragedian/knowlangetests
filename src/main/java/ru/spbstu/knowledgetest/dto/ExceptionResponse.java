package ru.spbstu.knowledgetest.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class ExceptionResponse {

    String message;
    String time;

    static String DEFAULT_MESSAGE = "Something went wrong during request processing";

    public ExceptionResponse() {
        this.message = DEFAULT_MESSAGE;
        this.time = LocalDateTime.now().toString();
    }

    public ExceptionResponse(String message) {
        this.message = message;
        this.time = LocalDateTime.now().toString();
    }
}
