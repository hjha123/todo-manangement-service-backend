package com.java.todo.service;

import com.java.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodo(long id, TodoDto todoDto);
    void deleteTodo(long todoId);
    TodoDto completeTodo(long todoId);
    TodoDto inCompleteTodo(long todoId);
}
