package ru.spbstu.knowledgetest.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionStatistics {

    double y;
    String label;
}
