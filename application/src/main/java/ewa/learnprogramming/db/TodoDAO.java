package ewa.learnprogramming.db;

import ewa.learnprogramming.core.Todo;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TodoDAO extends AbstractDAO<Todo> {

    public TodoDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Todo findById(Long id){
        return get(id);
    }
    public Todo create(Todo todo){
        return persist(todo);
    }
//    public Todo update(Todo todo){
//        return update(todo);
//    }

    public void delete(Todo todo){
        currentSession().delete(todo);
    }
    public List<Todo> findAll(){
        return list((Query<Todo>) namedQuery("ewa.learnprogramming.Todo_Ewa.application.core.Todo.findAll"));
    }
}
