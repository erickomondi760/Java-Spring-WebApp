package com.user.web_app.controller;


import com.user.web_app.dto.UserDTO;
import com.user.web_app.service.OccupationService;
import com.user.web_app.service.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app")
public class OccupationController {

    @Autowired
    private OccupationService occupationService;

    @PostMapping("occupation")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDTO userDTO){
        occupationService.createOccupation(userDTO);
        return ResponseEntity.ok("A new user has been created");
    }

    @PutMapping("occupation")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO){
        occupationService.updateUser(userDTO);
        return ResponseEntity.ok("User information have been updated");
    }

    @GetMapping("occupation/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id){
        return ResponseEntity.ok(occupationService.findUser(id));
    }

    @GetMapping("occupation")
    public ResponseEntity<UserResponse> getUsers(
            @RequestParam(name="pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = "asc",required = false) String sortOrder,
            @RequestParam(name = "pageSize",defaultValue = "25",required = false)int pageSize){

        return ResponseEntity.ok(occupationService.findAll(pageNumber,pageSize,sortOrder,sortBy));
    }

    @DeleteMapping("occupation/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        occupationService.deleteUser(id);
        return ResponseEntity.ok("User successfully deleted");
    }
}
