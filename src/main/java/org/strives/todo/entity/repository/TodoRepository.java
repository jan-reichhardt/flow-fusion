package org.strives.todo.entity.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.strives.todo.entity.Todo;

@ApplicationScoped
public class TodoRepository implements PanacheRepository<Todo> {

}
