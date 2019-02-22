package ru.hh.school.servers.resource;

import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.school.servers.model.TodoDto;
import ru.hh.school.servers.model.TodoItem;
import ru.hh.school.servers.service.TodoService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PATCH;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/todo/")
public class TodoResource {

    private TodoService service;

    public TodoResource(TodoService service) {
        this.service = service;
    }

    @GET
    @Path("/{todo_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodoById(@PathParam("todo_id") int todoId) {
        System.out.println("GET todo " + todoId);
        TodoItem item = service.getTodoById(todoId);
        if (item == null) {
            return Response.status(404).build();
        }
        return Response.ok().entity(Stream.of(item).map(TodoDto::new).collect(Collectors.toList())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTodo(@RequestBody TodoDto newItem) {
        System.out.println("POST todo");
        service.addTodo(new TodoItem(newItem.getTitle(), newItem.isCompleted()));
    }

    @PATCH
    @Path("/{todo_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTodo(@PathParam("todo_id") int todoId, @RequestBody TodoDto item) {
        System.out.println("PUT todo " + todoId);
        service.updateTodo(todoId, new TodoItem(item.getTitle(), item.isCompleted()));
    }

    @DELETE
    @Path("/{todo_id}")
    public void deleteTodo(@PathParam("todo_id") int todoId) {
        System.out.println("DELETE todo " + todoId);
        service.deleteTodo(todoId);
    }
}
