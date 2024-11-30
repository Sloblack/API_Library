package com.libraryproject.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AuthorRequestDTO {

    private Integer idAuthor;
    private String authorName;
    private String surname;
    private String nationality;
}
