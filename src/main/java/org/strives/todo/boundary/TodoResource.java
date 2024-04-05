package org.strives.todo.boundary;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.strives.todo.boundary.dto.TodoRequestDTO;
import org.strives.todo.control.TodoService;
import org.strives.todo.entity.Todo;

import java.util.List;

@Path("/todo")
@ApplicationScoped
public class TodoResource {

    @Inject
    TodoService todoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createTodoItem(TodoRequestDTO todoRequestDTO) {
        return todoService.create(todoRequestDTO);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Todo> getTodoItem(@PathParam("id") Long id) {
        return todoService.getTodoItem(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Todo>> getAllTodoItems() {
        return todoService.getAllTodoItems();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateTodoItem(@PathParam("id") Long id, TodoRequestDTO todoRequestDTO) {
        return todoService.update(id, todoRequestDTO);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> deleteTodoItem(@PathParam("id") Long id) {
        return todoService.delete(id);
    }
}
