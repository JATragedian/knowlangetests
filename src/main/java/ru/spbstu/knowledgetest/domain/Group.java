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
@Document("groups")
public class Group {

    @Id
    String id;
    @Field
    final String name;
    @Field
    final List<String> userIds;
}
