package br.mark.github.todoList.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "table_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String username;
    private String password;
}
