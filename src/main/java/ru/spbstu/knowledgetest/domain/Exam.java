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
@FieldDefaults(level= AccessLevel.PRIVATE)
@Document("exams")
public class Exam {

    // TODO: We need some statistics on question types by Bloom! We need to introduce different question types by Bloom
    //  and each exam must contain significant number of each type, or else it's not valid. Exam owners maybe could set amounts of such types.

    @Id
    String id;
    @Field
    final String name;
    @Field
    final String ownerId;
    @Field
    final String description;
    @Field
    final  int timeLimit;
    @Field
    final List<String> questionIds;
}
