package net.tchinchow.vuejs_spring_boot.spring_backend.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.tchinchow.vuejs_spring_boot.spring_backend.entity.User;
import net.tchinchow.vuejs_spring_boot.spring_backend.service.UsersService;

/**
 * @author JavaSolutionsGuide
 *
 */
@RestController
public class UsersRestController {

    @Autowired
    private UsersService usersService;

    public void setUserService(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        List<User> users = usersService.getUsers();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUser(@PathVariable(name = "userId") Long userId) {
        return usersService.getUser(userId);
    }

    @PostMapping("/api/users")
    public void saveUser(User user) {
        usersService.saveUser(user);
        System.out.println("User Saved Successfully");
    }

    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable(name = "userId") Long userId) {
        usersService.deleteUser(userId);
        System.out.println("User Deleted Successfully");
    }

    @PutMapping("/api/users/{userId}")
    public void updateUser(@RequestBody User user, @PathVariable(name = "userId") Long userId) {
        User storedUser = usersService.getUser(userId);
        if (storedUser != null) {
            usersService.updateUser(user);
        }
    }

}