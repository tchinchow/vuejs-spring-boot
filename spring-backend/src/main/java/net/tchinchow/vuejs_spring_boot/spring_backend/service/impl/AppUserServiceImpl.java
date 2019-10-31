package net.tchinchow.vuejs_spring_boot.spring_backend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppUser;
import net.tchinchow.vuejs_spring_boot.spring_backend.repository.AppUserRepository;
import net.tchinchow.vuejs_spring_boot.spring_backend.service.AppUserService;

/**
 * @author JavaSolutionsGuide
 *
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository usersRepository;

    @Override
    public List<AppUser> getUsers() {
        List<AppUser> users = usersRepository.findAll();
        return users;
    }

    @Override
    public AppUser getUser(Long userId) throws NoSuchElementException {
        Optional<AppUser> optEmp = usersRepository.findById(userId);
        return optEmp.get();
    }

    @Override
    public void saveUser(AppUser user) {
        usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public void updateUser(AppUser user) {
        usersRepository.save(user);
    }
}