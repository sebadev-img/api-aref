package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.response.UsersResponseDto;
import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.IUserService;
import dev.seba.apiaref.service.Impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final IUserService userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UsersResponseDto> getAll(){
        UsersResponseDto response = userService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User response = userService.getUserById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<User> searchUser(@RequestParam(required = false) String username, @RequestParam(required = false) String email){
        User response;
        if(username != null){
            response = userService.getUserByUsername(username);
        } else if (email != null) {
            response = userService.getUserByEmail(email);
        }
        else{
            response = null;
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/cities/{name}")
    public ResponseEntity<UsersResponseDto> getUsersByCity(@PathVariable String name){
        UsersResponseDto response = userService.getUsersByCity(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}