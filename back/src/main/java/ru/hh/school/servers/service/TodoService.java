package ru.hh.school.servers.service;

import ru.hh.school.servers.dao.TodoDao;

public class TodoService {

    private TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }
}
