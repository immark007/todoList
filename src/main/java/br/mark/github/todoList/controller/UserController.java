package br.mark.github.todoList.controller;

import br.mark.github.todoList.dto.CreateUserDto;
import br.mark.github.todoList.dto.UpdateUserDto;
import br.mark.github.todoList.entity.User;
import br.mark.github.todoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Validated CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        var user = userService.getUserById(UUID.fromString(userId));
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
        var usersPage = userService.listUser(pageable);
        return ResponseEntity.ok(usersPage);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserById(UUID.fromString(userId), updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUserById(UUID.fromString(userId));
        return ResponseEntity.noContent().build();
    }


}
