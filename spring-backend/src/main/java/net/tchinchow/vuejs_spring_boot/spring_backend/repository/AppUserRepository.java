package net.tchinchow.vuejs_spring_boot.spring_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppUser;
 
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    @Query("SELECT u FROM AppUser u " + " WHERE u.email = ?1")
    Optional<AppUser> findUserByEmail(String email);
}
