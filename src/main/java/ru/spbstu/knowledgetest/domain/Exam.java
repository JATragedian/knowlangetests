package ru.spbstu.knowledgetest.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("exams")
public class Exam {

    @Id
    String id;
    @Field
    String name;
    @Field
    String ownerId;
    @Field
    String description;
    @Field
    int timeLimit;
    @Field
    List<Question> questions;
}
