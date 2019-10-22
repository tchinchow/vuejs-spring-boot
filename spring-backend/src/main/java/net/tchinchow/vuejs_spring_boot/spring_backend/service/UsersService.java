package net.tchinchow.vuejs_spring_boot.spring_backend.service;

import java.util.List;

import net.tchinchow.vuejs_spring_boot.spring_backend.entity.User;

/**
 * @author JavaSolutionsGuide
 *
 */
public interface UsersService {
    public List<User> getUsers();

    public User getUser(Long userId);

    public void saveUser(User user);

    public void deleteUser(Long userId);

    public void updateUser(User user);
}