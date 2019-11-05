package net.tchinchow.vuejs_spring_boot.spring_backend.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppUser;
import net.tchinchow.vuejs_spring_boot.spring_backend.service.AppUserService;

/**
 * @author JavaSolutionsGuide
 *
 */
@RestController
@RequestMapping("/api/private/user")
public class UserRestController {

    @Autowired
    private AppUserService userService;

    public void setUserService(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<AppUser> getUsers() {
        List<AppUser> users = userService.getUsers();
        return users;
    }

    @GetMapping("/{userId}")
    public AppUser getUser(@PathVariable(name = "userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/")
    public void saveUser(AppUser user) {
        userService.saveUser(user);
        System.out.println("User Saved Successfully");
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        System.out.println("User Deleted Successfully");
    }

    @PutMapping("/{userId}")
    public void updateUser(@RequestBody AppUser user, @PathVariable(name = "userId") Long userId) {
        AppUser storedUser = userService.getUser(userId);
        if (storedUser != null) {
            userService.updateUser(user);
        }
    }

}