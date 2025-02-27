package com.java.todo.service.impl;

import com.java.todo.dto.TodoDto;
import com.java.todo.entity.Todo;
import com.java.todo.exception.ResourceNotFoundException;
import com.java.todo.repository.TodoRepository;
import com.java.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodo(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Todo with given id " + id + " doesn't exist"));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = todoRepository.findAll();

        return todoList.stream().map(todo -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Todo with given id " + id + " doesn't exist"));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
                new ResourceNotFoundException("Todo with given id " + todoId + " doesn't exist"));

        todoRepository.deleteById(todoId);

    }

    @Override
    public TodoDto completeTodo(long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
                new ResourceNotFoundException("Todo with given id " + todoId + " doesn't exist"));

        todo.setCompleted(Boolean.TRUE);

        Todo completedTodo = todoRepository.save(todo);

        return modelMapper.map(completedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
                new ResourceNotFoundException("Todo with given id " + todoId + " doesn't exist"));

        todo.setCompleted(Boolean.FALSE);

        Todo completedTodo = todoRepository.save(todo);

        return modelMapper.map(completedTodo, TodoDto.class);
    }
}
