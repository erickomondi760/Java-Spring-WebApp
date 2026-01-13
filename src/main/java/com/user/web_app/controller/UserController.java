package com.user.web_app.controller;


import com.user.web_app.dto.UserDTO;
import com.user.web_app.dto.UserResponse;
import com.user.web_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable int id){
        return ResponseEntity.ok(userService.updateUser(userDTO,id));
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

    @GetMapping("users/by-occupation/{occupation}")
    public ResponseEntity<UserResponse> getUsersByOccupation(
            @RequestParam(name="pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = "asc",required = false) String sortOrder,
            @RequestParam(name = "pageSize",defaultValue = "25",required = false)int pageSize,
            @PathVariable String occupation){

        return ResponseEntity.ok(userService.findUsersByOccupation(pageNumber,pageSize,sortOrder,sortBy,occupation));
    }
}
