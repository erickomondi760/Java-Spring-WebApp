package com.user.web_app.service;

import com.user.web_app.dto.UserDTO;
import com.user.web_app.entity.User;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface UserService {
    void createUser(UserDTO userDTO);

    void updateUser(@Valid UserDTO userDTO);

    UserDTO findUser(int id);

    UserResponse findAll(int pageNumber, int pageSize, String sortOrder, String sortBy);

    void deleteUser(int id);

    UserResponse findUsersByOccupation(int pageNumber, int pageSize, String sortOrder,
                                                 String sortBy, String occupation);
}
