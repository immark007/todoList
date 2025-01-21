package br.mark.github.todoList.service;

import br.mark.github.todoList.dto.CreateUserDto;
import br.mark.github.todoList.entity.User;
import br.mark.github.todoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UUID createUser(CreateUserDto createUserDto) {
        var entity = new User(createUserDto.username(),createUserDto.email(),createUserDto.password());
        var userSaved = userRepository.save(entity);
        return userSaved.getId();
    }
}
