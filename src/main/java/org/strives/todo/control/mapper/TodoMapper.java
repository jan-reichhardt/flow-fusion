package org.strives.todo.control.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.strives.todo.boundary.dto.TodoRequestDTO;
import org.strives.todo.entity.Todo;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    Todo toEntity(TodoRequestDTO todoRequestDTO);
}
