package ru.hh.school.servers.dao;

import ru.hh.school.servers.model.TodoItem;

import java.util.List;

public interface TodoDao {

    public TodoItem getTodoById(Integer todoId);
    public List<TodoItem> getAllTodos();
    public void addTodo(TodoItem todoItem);
    public void updateTodo(int todoId, TodoItem todoItem);
    public void deleteTodo(int todoId);
    public void deleteAll();
}
