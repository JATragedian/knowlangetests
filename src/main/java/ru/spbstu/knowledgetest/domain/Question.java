package ru.spbstu.knowledgetest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.spbstu.knowledgetest.enums.BloomLevel;
import ru.spbstu.knowledgetest.enums.QuestionType;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Document("questions")
public class Question {

    @Id
    String id;
    @Field
    final String content;
    @Field
    final QuestionType type;
    @Field
    final BloomLevel level;
    @Field
    final int weight;
    @Field
    final List<String> correctAnswers;
}
