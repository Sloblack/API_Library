package com.libraryproject.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "authors")
public class AuthorResource {

    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String authorName;
    private String surname;
    private String nationality;

}
