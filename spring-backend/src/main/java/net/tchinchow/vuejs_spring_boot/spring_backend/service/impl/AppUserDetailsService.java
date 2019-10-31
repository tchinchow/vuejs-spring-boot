package net.tchinchow.vuejs_spring_boot.spring_backend.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppPrivilege;
import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppRole;
import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppUser;
import net.tchinchow.vuejs_spring_boot.spring_backend.repository.AppUserRepository;

@Service("userDetailsService")
@Transactional
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    // @Autowired
    // private IUserService service;

    // @Autowired
    // private MessageSource messages;

    // @Autowired
    // private AppRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AppUser> user = userRepository.findUserByEmail(email);
        // Optional<AppUser> user = userRepository.findById(0L);
        if (user.isPresent()) {
            return new User(user.get().getEmail(), user.get().getPassword(), user.get().isEnabled(), true, true, true,
                    getAuthorities(user.get().getRoles()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<AppRole> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<AppRole> roles) {

        List<String> privileges = new ArrayList<>();
        List<AppPrivilege> collection = new ArrayList<>();
        for (AppRole role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (AppPrivilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}