package org.strives.todo.control;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.strives.todo.boundary.dto.TodoRequestDTO;
import org.strives.todo.boundary.response.SuccessResponse;
import org.strives.todo.control.mapper.TodoMapper;
import org.strives.todo.entity.Todo;
import org.strives.todo.entity.repository.TodoRepository;

import java.util.List;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class TodoService {

    @Inject
    TodoMapper todoMapper;

    @Inject
    TodoRepository todoRepository;

    @WithTransaction
    public Uni<Response> create(TodoRequestDTO todoRequestDTO) {
        Todo todo = todoMapper.toEntity(todoRequestDTO);

        return todoRepository.persist(todo).onItem().transformToUni(item -> {
            boolean isPersisted = item != null;

            if (!isPersisted) {
                return Uni.createFrom().item(Response.notModified("Item is not persisted!").build());
            }

            return Uni.createFrom().item(Response.ok(new SuccessResponse("Item successfully created!", null)).build());
        });
    }

    @WithSession
    public Uni<List<Todo>> getAllTodoItems() {
        return todoRepository.listAll();
    }

    @WithSession
    public Uni<Todo> getTodoItem(Long id) {
        return todoRepository.findById(id);
    }

    @WithTransaction
    public Uni<Response> update(Long id, TodoRequestDTO todoRequestDTO) {
        return todoRepository.findById(id).flatMap(todo -> {
            if (!todoRequestDTO.getName().isEmpty()) {
                todo.setName(todoRequestDTO.getName());
            }

            if (!todoRequestDTO.getStatus().isEmpty()) {
                todo.setStatus(todoRequestDTO.getStatus());
            }

            return todoRepository.persist(todo).onItem().transformToUni(item -> {
                boolean isPersisted = item != null;

                if (!isPersisted) {
                    return Uni.createFrom().item(Response.notModified("Item is not modified!").build());
                }

                return Uni.createFrom().item(Response.ok(new SuccessResponse("Item successfully updated!", id)).build());
            });
        });
    }

    @WithTransaction
    public Uni<Response> delete(Long id) {
        return todoRepository.deleteById(id).onItem().transformToUni(item -> {
            if (Boolean.FALSE.equals(item)) {
                return Uni.createFrom().item(Response.notModified("Item is not deleted!").build());
            }

            return Uni.createFrom().item(Response.ok(new SuccessResponse("Item successfully deleted!", id)).build());
        });
    }
}
