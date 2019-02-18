package ru.hh.school.servers.dto;

public class TodoDto {
    private int id;
    private String title;
    private boolean completed;

    public TodoDto() {}

    public TodoDto(String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public TodoDto(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }
}
