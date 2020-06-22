package ewa.learnprogramming.resources;


import ewa.learnprogramming.core.Todo;
import ewa.learnprogramming.db.TodoDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final TodoDAO todoDAO;

    public TodoResource(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    @UnitOfWork
    @GET
    @Path("/get")
    public List<Todo> getTodos(){
        return todoDAO.findAll();
    }
    @UnitOfWork
    @POST
    public Todo createTodo(@Valid Todo todo){
        return todoDAO.create(todo);
    }

    @UnitOfWork
    @GET
    @Path("{id}")
    public Todo getTodo( @PathParam("id") Long id) {
        return todoDAO.findById(id);
    }

//    @UnitOfWork
//    @PUT
//    @Path("{id}")
//    public Todo updateTodo(@PathParam("id") Long id) {
//        return todoDAO.update(getTodo(id));
//    }

    @UnitOfWork
    @DELETE
    @Path("{id}")
    public void deleteTodo(@PathParam("id") Long id) {
            todoDAO.delete(getTodo(id));
        }
}
