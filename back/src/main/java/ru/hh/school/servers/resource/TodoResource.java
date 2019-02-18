package ru.hh.school.servers.resource;

import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.school.servers.dto.TodoDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;

@Path("/")
public class TodoResource {

    private LinkedList<TodoDto> list = new LinkedList<>();

    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTodos() {
        System.out.println("GET todos");
        return Response.ok().entity(list.toArray()).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/todos/active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveTodos() {
        System.out.println("GET active todos");
        LinkedList<TodoDto> activeList = new LinkedList<>();
        for (TodoDto listItem: list) {
            if (!listItem.isCompleted()) {
                activeList.add(listItem);
            }
        }
        return Response.ok().entity(activeList.toArray()).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/todos/completed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedTodos() {
        System.out.println("GET completed todos");
        LinkedList<TodoDto> completedList = new LinkedList<>();
        for (TodoDto listItem: list) {
            if (listItem.isCompleted()) {
                completedList.add(listItem);
            }
        }
        return Response.ok().entity(completedList.toArray()).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/todo/{todo_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodoById(@PathParam("todo_id") int todoId) {
        System.out.println("GET todo " + todoId);
        LinkedList<TodoDto> todoList = new LinkedList<>();
        for (TodoDto todo: list) {
            if (todo.getId()==todoId) {
                todoList.add(todo);
            }
        }
        return Response.ok().entity(todoList.toArray()).header("Access-Control-Allow-Origin", "*").build();
        //return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/todo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTodo(@RequestBody TodoDto newItem) {
        System.out.println("POST todo");
        if (list.isEmpty()) {
            newItem.setId(1);
        } else {
            newItem.setId(list.getLast().getId()+1);
        }
        list.add(newItem);
    }

    @PUT
    @Path("/todo/{todo_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTodo(@PathParam("todo_id") int todoId, @RequestBody TodoDto item) {
        System.out.println("PUT todo " + todoId);
        for (TodoDto listItem: list) {
            if (listItem.getId() == todoId) {
                listItem.setTitle(item.getTitle());
                listItem.setCompleted(item.isCompleted());
            }
        }
    }

    @DELETE
    @Path("/todo/{todo_id}")
    public void deleteTodo(@PathParam("todo_id") int todoId) {
        System.out.println("DELETE todo " + todoId);
        for (TodoDto listItem: list) {
            if (listItem.getId() == todoId) {
                list.remove(listItem);
            }
        }
    }

    @DELETE
    @Path("/todos")
    public void deleteAll() {
        System.out.println("DELETE all todos");
        list.clear();
    }
}
