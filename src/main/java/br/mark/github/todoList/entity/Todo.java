package br.mark.github.todoList.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Alterado de Long para UUID

    private String title;
    private String description;

    @ManyToOne // Corrigido para @ManyToOne
    @JoinColumn(name = "user_id") // Define a chave estrangeira
    private User user;

    // Construtores
    public Todo() {}

    public Todo(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
