package com.user.web_app.controller;


import com.user.web_app.dto.UserDTO;
import com.user.web_app.service.UserResponse;
import com.user.web_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.ok("A new user has been created");
    }

    @PutMapping("users")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return ResponseEntity.ok("User information have been updated");
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id){
        return ResponseEntity.ok(userService.findUser(id));
    }

    @GetMapping("users")
    public ResponseEntity<UserResponse> getUsers(
            @RequestParam(name="pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = "asc",required = false) String sortOrder,
            @RequestParam(name = "pageSize",defaultValue = "25",required = false)int pageSize){

        return ResponseEntity.ok(userService.findAll(pageNumber,pageSize,sortOrder,sortBy));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User successfully deleted");
    }

    @GetMapping("users/{occupation}")
    public ResponseEntity<UserResponse> getUsersByOccupation(
            @RequestParam(name="pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = "asc",required = false) String sortOrder,
            @RequestParam(name = "pageSize",defaultValue = "25",required = false)int pageSize,
            @PathVariable String occupation){

        return ResponseEntity.ok(userService.findUsersByOccupation(pageNumber,pageSize,sortOrder,sortBy,occupation));
    }
}
