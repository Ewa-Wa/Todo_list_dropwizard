package ewa.learnprogramming.db;

import ewa.learnprogramming.core.Todo;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
class TodoDAOTest {

    public DAOTestExtension daoTestExtension = DAOTestExtension.newBuilder()
            .addEntityClass(Todo.class).build();

    private TodoDAO todoDAO;


    @BeforeEach
    public void setUp(){
        todoDAO = new TodoDAO(daoTestExtension.getSessionFactory());
    }

    @Test
    void create() {
        Todo item = daoTestExtension.inTransaction(()-> todoDAO.create(new Todo("work", "prepare new image")));
        assertThat(item.getId()).isGreaterThan(0);
        assertThat(item.getTitle()).isEqualTo("work");
        assertThat(item.getContent()).isEqualTo("prepare new image");
        assertThat(todoDAO.findById(item.getId())).isEqualTo(item);
    }


    @Test
    void delete() {
        daoTestExtension.inTransaction(()-> {
            todoDAO.create(new Todo("work", "prepare new image"));
            todoDAO.create(new Todo("home", "clean mirrors"));
            todoDAO.create(new Todo("money", "withdraw cash"));
            todoDAO.delete(todoDAO.findById(1L));
        });
        assertThat(todoDAO.findAll()).extracting("title").containsOnly( "home", "money");
    }

    @Test
    void findAll() {
        daoTestExtension.inTransaction(()-> {
                todoDAO.create(new Todo("work", "prepare new image"));
                todoDAO.create(new Todo("home", "clean mirrors"));
                todoDAO.create(new Todo("money", "withdraw cash"));
        });
        List<Todo> todos = todoDAO.findAll();
        assertThat(todos).extracting("title").containsOnly("work", "home", "money");
        assertThat(todos).extracting("content").containsOnly("prepare new image",
                "clean mirrors", "withdraw cash");
    }
}