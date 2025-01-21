package br.mark.github.todoList.service;

import br.mark.github.todoList.dto.CreateUserDto;
import br.mark.github.todoList.dto.UpdateUserDto;
import br.mark.github.todoList.entity.User;
import br.mark.github.todoList.repository.UserRepository;
import br.mark.github.todoList.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UUID createUser(CreateUserDto createUserDto) {
        if (userRepository.findByEmail(createUserDto.email()).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso!");
        }

        var entity = new User(createUserDto.username(), createUserDto.email(), createUserDto.password());
        var userSaved = userRepository.save(entity);
        return userSaved.getId();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUserById(UUID userId, UpdateUserDto updateUserDto) {
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário com ID " + userId + " não encontrado."));

        Optional.ofNullable(updateUserDto.username()).ifPresent(user::setUsername);
        Optional.ofNullable(updateUserDto.password()).ifPresent(user::setPassword);

        userRepository.save(user);
    }

    @Transactional
    public String deleteUserById(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Usuário com ID " + userId + " não encontrado.");
        }
        userRepository.deleteById(userId);
        return "Usuário deletado com sucesso!";
    }
}
