package ru.hh.school.servers.dao.impl;

import ru.hh.school.servers.dao.TodoDao;
import ru.hh.school.servers.model.TodoItem;

import java.util.LinkedList;
import java.util.List;


public class TodoDaoCollectionsImpl implements TodoDao {

    private LinkedList<TodoItem> todoList;

    public TodoDaoCollectionsImpl() {
        todoList = new LinkedList<>();
    }

    @Override
    public TodoItem getTodoById(Integer todoId) {
        for (TodoItem item : todoList) {
            if (item.getId() == todoId) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<TodoItem> getAllTodos() {
        return todoList;
    }

    @Override
    public void addTodo(TodoItem todoItem) {
        if (todoList.isEmpty()) {
            todoItem.setId(1);
        } else {
            todoItem.setId(todoList.getLast().getId()+1);
        }
        todoList.add(todoItem);
    }

    @Override
    public void updateTodo(int todoId, TodoItem todoItem) {
        TodoItem item = getTodoById(todoId);
        if (todoItem.getTitle()==null) {
            item.setCompleted(todoItem.isCompleted());
        } else {
            item.setTitle(todoItem.getTitle());
        }
    }

    @Override
    public void deleteTodo(int todoId) {
        todoList.remove(getTodoById(todoId));
    }

    @Override
    public void deleteAll() {
        todoList.clear();
    }
}
