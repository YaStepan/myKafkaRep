package com.nerzon.course.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CatDTO {
    String name;
    int age;
    int weight;
}
