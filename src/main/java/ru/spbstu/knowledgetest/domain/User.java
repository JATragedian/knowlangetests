package ru.spbstu.knowledgetest.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.spbstu.knowledgetest.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Document("users")
public class User {

    @Id
    String id;
    @Field
    String name;
    @Field
    String surname;
    @Field
    String email;
    @Field
    UserRole role;
    @Field
    String password;
}
