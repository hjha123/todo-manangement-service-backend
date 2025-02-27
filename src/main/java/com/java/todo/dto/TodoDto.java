package com.java.todo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDto {
    private long id;
    private String title;
    private String description;
    private boolean completed;
}
