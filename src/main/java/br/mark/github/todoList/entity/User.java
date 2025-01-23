package br.mark.github.todoList.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "table_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todoList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_perfil",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfis = new ArrayList<>();

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    // Getters e Setters
    // Getters e Setters padrão
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Indica se a conta do usuário está expirada
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Indica se a conta do usuário está bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Indica se as credenciais do usuário (senha) estão expiradas
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Indica se a conta do usuário está habilitada
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna as permissões (roles) do usuário
        return this.perfis;
    }

}
