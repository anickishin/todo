package ru.hh.school.servers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.school.servers.dao.TodoDao;
import ru.hh.school.servers.service.TodoService;

@Configuration
@Import(NabProdConfig.class)
public class CommonConfig {

    @Bean
    public TodoDao todoDao() {
        return new TodoDao();
    }

    @Bean
    public TodoService todoService() {
        return new TodoService(todoDao());
    }
}
