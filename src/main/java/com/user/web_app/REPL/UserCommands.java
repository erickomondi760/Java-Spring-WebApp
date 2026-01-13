package com.user.web_app.REPL;

import com.user.web_app.entity.Occupation;
import com.user.web_app.entity.User;
import com.user.web_app.repository.OccupationRepository;
import com.user.web_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class UserCommands {

    private final UserRepository userRepository;

    private final OccupationRepository occupationRepository;

    public UserCommands(@Autowired UserRepository userRepository,
                        @Autowired OccupationRepository occupationRepository) {
        this.userRepository = userRepository;
        this.occupationRepository = occupationRepository;
    }

    @ShellMethod(key = "list users", value = "List all users")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @ShellMethod(key = "get user", value = "Get user by id")
    public User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @ShellMethod(key = "delete user", value = "Delete user by id")
    public String deleteUser(int id) {
        userRepository.deleteById(id);
        return "Deleted user " + id;
    }

    @ShellMethod(key = "list occupations", value = "List all occupation")
    public List<Occupation> listOccupations() {
        return occupationRepository.findAll();
    }

    @ShellMethod(key = "get occupation", value = "Get occupation by id")
    public Occupation getOccupation(int id) {
        return occupationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Occupation not found"));
    }

    @ShellMethod(key = "delete occupation", value = "Delete occupation by id")
    public String deleteOccupation(int id) {
        userRepository.deleteById(id);
        return "Deleted occupation " + id;
    }
}
