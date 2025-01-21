package br.mark.github.todoList.controller;

import br.mark.github.todoList.dto.CreateUserDto;
import br.mark.github.todoList.entity.User;
import br.mark.github.todoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
