package ru.hh.school.servers.resource;

import ru.hh.school.servers.model.TodoDto;
import ru.hh.school.servers.service.TodoService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/todos")
public class TodosResource {

    private TodoService service;

    public TodosResource(TodoService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTodos() {
        System.out.println("GET todos");
        return Response.ok().entity(service.getAllTodos().stream().map(TodoDto::new).collect(Collectors.toList())).build();
    }

    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveTodos() {
        System.out.println("GET active todos");
        return Response.ok().entity(service.getActiveTodos().stream().map(TodoDto::new).collect(Collectors.toList())).build();
    }

    @GET
    @Path("/completed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedTodos() {
        System.out.println("GET completed todos");
        return Response.ok().entity(service.getCompletedTodos().stream().map(TodoDto::new).collect(Collectors.toList())).build();
    }

    @DELETE
    public void deleteAll() {
        System.out.println("DELETE all todos");
        service.deleteAll();
    }
}
