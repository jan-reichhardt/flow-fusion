package org.strives.todo.boundary.dto;

import lombok.Data;

@Data
public class TodoRequestDTO {
    private String name;

    private String status;
}
