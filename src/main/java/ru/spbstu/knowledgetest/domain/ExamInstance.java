package ru.spbstu.knowledgetest.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.spbstu.knowledgetest.enums.ExamStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Document("exam-instances")
public class ExamInstance {

    @Id
    String id;
    @Field
    final String examId;
    @Field
    final String studentId;
    @Field
    ExamStatus examStatus;
    @Field
    int score;
    @Field
    LocalDateTime startedTime;

    public ExamInstance(String examId, String studentId) {
        this.examId = examId;
        this.studentId = studentId;
        this.examStatus = ExamStatus.AVAILABLE;
        this.score = 0;
    }
}
