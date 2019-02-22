package ru.hh.school.servers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.servers.resource.FrontResource;
import ru.hh.school.servers.resource.TodoResource;
import ru.hh.school.servers.resource.TodosResource;
import ru.hh.school.servers.service.TodoService;

@Configuration
@Import(FrontResource.class)
public class JerseyConfig {

    private TodoService service;

    public JerseyConfig(TodoService service) {
        this.service = service;
    }

    @Bean
    public TodoResource todoResource() {
        return new TodoResource(service);
    }

    @Bean
    public TodosResource todosResource() {
        return new TodosResource(service);
    }

}
