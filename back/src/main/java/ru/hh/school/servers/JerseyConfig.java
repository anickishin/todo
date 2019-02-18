package ru.hh.school.servers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.servers.resource.TodoResource;

@Configuration
@Import(TodoResource.class)
public class JerseyConfig {

}
