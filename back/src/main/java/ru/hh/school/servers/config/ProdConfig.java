package ru.hh.school.servers.config;

import org.springframework.context.annotation.Bean;
import ru.hh.school.servers.dao.TodoDao;
import ru.hh.school.servers.dao.impl.TodoDaoCollectionsImpl;

public class ProdConfig {

    @Bean
    public TodoDao todoDao() {
        return new TodoDaoCollectionsImpl();
    }
}
