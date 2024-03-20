package org.strives.todo.control.mapper;

import org.mapstruct.Mapper;
import org.strives.todo.boundary.dto.TodoRequestDTO;
import org.strives.todo.boundary.dto.TodoResponseDTO;
import org.strives.todo.entity.Todo;

@Mapper(componentModel = "cdi")
public interface TodoMapper {

    TodoResponseDTO toResponseDTO(Todo todo);

    Todo toEntity(TodoRequestDTO todoRequestDTO);
}
