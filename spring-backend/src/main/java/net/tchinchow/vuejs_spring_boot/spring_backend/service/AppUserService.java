package net.tchinchow.vuejs_spring_boot.spring_backend.service;

import java.util.List;

import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppUser;

/**
 * @author JavaSolutionsGuide
 *
 */
public interface AppUserService {
    public List<AppUser> getUsers();

    public AppUser getUser(Long userId);

    public void saveUser(AppUser user);

    public void deleteUser(Long userId);

    public void updateUser(AppUser user);
}