package ru.hh.school.servers.service;

import ru.hh.school.servers.dao.TodoDao;
import ru.hh.school.servers.model.TodoItem;

import java.util.List;
import java.util.stream.Collectors;

public class TodoService {

    private TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public TodoItem getTodoById(Integer todoId) {
        return todoDao.getTodoById(todoId);
    }

    public List<TodoItem> getActiveTodos() {
        return todoDao.getAllTodos().stream().filter((todoItem) -> !todoItem.isCompleted()).collect(Collectors.toList());
    }

    public List<TodoItem> getCompletedTodos() {
        return todoDao.getAllTodos().stream().filter((todoItem) -> todoItem.isCompleted()).collect(Collectors.toList());
    }

    public List<TodoItem> getAllTodos() {
        return todoDao.getAllTodos();
    }

    public void addTodo(TodoItem todoItem) {
        todoDao.addTodo(todoItem);
    }

    public void updateTodo(int todoId, TodoItem todoItem) {
        todoDao.updateTodo(todoId, todoItem);
    }

    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }

    public void deleteAll() {
        todoDao.deleteAll();
    }
}
