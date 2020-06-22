package ewa.learnprogramming;

import ewa.learnprogramming.core.Todo;
import ewa.learnprogramming.db.TodoDAO;
import ewa.learnprogramming.resources.TodoResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;


public class TodoApplication extends Application<TodoConfiguration> {

    private final HibernateBundle<TodoConfiguration> hibernate =
            new HibernateBundle<TodoConfiguration>(Todo.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(TodoConfiguration todoConfiguration) {
                    return todoConfiguration.getDataSourceFactory();
                }

                @Override
                protected void configure(Configuration configuration) {
                    super.configure(configuration.configure().addAnnotatedClass(Todo.class));// hbmddl
                }
            };

    public static void main(final String[] args) throws Exception {
        new TodoApplication().run(args);
    }


    @PostConstruct
    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {
        bootstrap.getConfigurationFactoryFactory();
        bootstrap.addBundle(hibernate);

    }


        @Override
    public void run(final TodoConfiguration configuration,
                    final Environment environment) throws ClassNotFoundException {


         final TodoDAO dao = new TodoDAO(hibernate.getSessionFactory());
         environment.jersey().register(new TodoResource(dao));


    }

    @Override
    public String getName() {
        return super.getName();
    }
}
