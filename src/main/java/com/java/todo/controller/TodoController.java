package com.java.todo.controller;

import com.java.todo.dto.TodoDto;
import com.java.todo.exception.ResourceNotFoundException;
import com.java.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
@Slf4j
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/addTodo")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todo) {
        log.info("Request received to add todo [{}]", todo);
        TodoDto todoDto = null;
        try {
            todoDto = todoService.addTodo(todo);
        } catch (Exception e) {
            log.error("Exception while adding todo | Exception :: [{}]", e.getMessage(), e);
        }
        return new ResponseEntity<>(todoDto, HttpStatus.CREATED);
    }

    @GetMapping("getTodo/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") long id) {
        log.info("Request received to get todo id :: [{}]", id);
        TodoDto todoDto = null;
        try {
            todoDto = todoService.getTodo(id);
        } catch (Exception e) {
            log.error("Exception while getting todo with id [{}] | Exception :: [{}]", id, e.getMessage(), e);
        }

        return ResponseEntity.ok(todoDto);
    }

    @GetMapping("/getAllTodos")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        log.info("Request received to get all todos.");
        List<TodoDto> todoList = null;
        try {
            todoList = todoService.getAllTodos();
        } catch (Exception e) {
            log.error("Exception while getting all todos  | Exception :: [{}]", e.getMessage(), e);
        }

        return ResponseEntity.ok(todoList);
    }

    @PutMapping("/updateTodo/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") long id, @RequestBody TodoDto todoDto) {
        log.info("Request received to update todo with id :: [{}]", id);
        TodoDto updatedTodo = null;
        try {
            updatedTodo = todoService.updateTodo(id, todoDto);

        } catch (ResourceNotFoundException ex) {
            log.error("Todo not found with id [{}]", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception while updating todo with id [{}] | Exception :: [{}]", id, e.getMessage(), e);
        }
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") long todoId) {
        log.info("Request received to delete todo with id :: [{}]", todoId);
        try {
            todoService.deleteTodo(todoId);

        } catch (Exception e) {
            log.error("Exception while deleting todo with id [{}] | Exception :: [{}]", todoId, e.getMessage(), e);
        }

        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PatchMapping("completeTodo/{id}")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") long todoId){
        log.info("Request received to mark the todo complete for todoId :: [{}]", todoId);
        TodoDto completeTodo = null;
        try {
            completeTodo = todoService.completeTodo(todoId);
        } catch (Exception e) {
            log.error("Exception while marking the todo complete for id [{}] | Exception :: [{}]", todoId, e.getMessage(), e);
        }
        return ResponseEntity.ok(completeTodo);
    }

    @PatchMapping("inCompleteTodo/{id}")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") long todoId){
        log.info("Request received to mark the todo complete for todoId :: [{}]", todoId);
        TodoDto completeTodo = null;
        try {
            completeTodo = todoService.inCompleteTodo(todoId);
        } catch (Exception e) {
            log.error("Exception while marking the todo complete for id [{}] | Exception :: [{}]", todoId, e.getMessage(), e);
        }
        return ResponseEntity.ok(completeTodo);
    }
}
