package com.user.web_app.service;

import com.user.web_app.dto.UserDTO;
import com.user.web_app.dto.UserResponse;
import jakarta.validation.Valid;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(@Valid UserDTO userDTO,int id);

    UserDTO findUser(int id);

    UserResponse findAll(int pageNumber, int pageSize, String sortOrder, String sortBy);

    void deleteUser(int id);

    UserResponse findUsersByOccupation(int pageNumber, int pageSize, String sortOrder,
                                                 String sortBy, String occupation);
}
