package ru.spbstu.knowledgetest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("exams")
public class Exam {

    @Id
    String id;
    @Field
    final String name;
    @Field
    final String ownerId;
    @Field
    final String description;
    @Field
    final int timeLimit;
    @Field
    final List<Question> questions;
}
