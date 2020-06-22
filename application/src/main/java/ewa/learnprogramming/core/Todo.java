package ewa.learnprogramming.core;

import javax.persistence.*;


@Entity
@NamedQueries(
        {@NamedQuery(name = "ewa.learnprogramming.Todo_Ewa.application.core.Todo.findAll",
        query = "SELECT p FROM Todo p")}
)

// @Id,@Column, @JoinColumn, @ManyToOne, @OneToMany @manyToMany rodzaje relacji
//@GeneratedValue
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public Todo( String title, String content) {
        this.title = title;
        this.content = content;
    }
    public Todo(){
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Todo todo = (Todo) obj;

        if(id != todo.id) return false;
        if(content != null ? !content.equals(todo.content) : todo.content != null) return false;        // może dodać not null --????????--
        if(!title.equals(todo.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    // == getters ==

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // == class ==

//    public static class Builder {
//        private long id;
//        private String title;
//        private Optional<String> content = Optional.absent();
//
//        private Builder(long id, String title) {
//            this.id = id;
//            this.title = title;
//        }
//
//        private Builder(Todo todo) {
//            this.id = todo.getId();
//            this.title = todo.getTitle();
//            this.content = todo.getContent();
//        }
//
//        public static Builder aTodo(long id, String title){
//            return new Builder(id, title);
//        }
//
//        public static Builder aTodo(Todo todo){
//            return new Builder(todo);
//        }
//
//        public Builder withId(long id){
//            this.id = id;
//            return this;
//        }
//
//        public Builder withTitle (String title){
//            this.title = title;
//            return this;
//        }
//
//        public Builder withContent (Optional<String> content) {
//            this.content = content;
//            return this;
//        }
//
//        public Todo build(){
//            Preconditions.checkNotNull(this.id, "id can't be null");
//            Preconditions.checkNotNull(this.title, "title can't be null");
//            Preconditions.checkNotNull(this.content, "content can't be null");
//            Todo todo = new Todo(id, title, content);
//            return todo;
//        }

    }

