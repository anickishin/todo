package ru.hh.school.servers.service;

import ru.hh.school.servers.dao.TodoDao;
import ru.hh.school.servers.model.TodoItem;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoService {

    private TodoDao todoDao;

    private LinkedList<TodoItem> list;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
        list =  new LinkedList<TodoItem>();
        list.add(new TodoItem(1, "sdfsd", true));
        list.add(new TodoItem(2, "fadwg", false));
    }

    public List<TodoItem> getAllTodos() {
        return list;
    }

    public List<TodoItem> getActiveTodos() {
        return list.stream().filter((todoItem) -> !todoItem.isCompleted()).collect(Collectors.toList());
    }

    public List<TodoItem> getCompletedTodos() {
        return list.stream().filter((todoItem) -> todoItem.isCompleted()).collect(Collectors.toList());
    }

    public void deleteAll() {
        list.clear();
    }

    public TodoItem getTodoById(Integer todoId) {
        for (TodoItem item : list) {
            if (item.getId() == todoId) {
                return item;
            }
        }
        return null;
    }

    public void addTodo(TodoItem todoItem) {
        if (list.isEmpty()) {
            todoItem.setId(1);
        } else {
            todoItem.setId(list.getLast().getId()+1);
        }
        list.add(todoItem);
    }

    public void updateTodo(int todoId, TodoItem todoItem) {
        TodoItem item = getTodoById(todoId);
        if (todoItem.getTitle()==null) {
            item.setCompleted(todoItem.isCompleted());
        } else {
            item.setTitle(todoItem.getTitle());
        }
    }

    public void deleteTodo(int todoId) {
        list.remove(getTodoById(todoId));
    }
}
