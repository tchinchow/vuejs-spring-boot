package net.tchinchow.vuejs_spring_boot.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import net.tchinchow.vuejs_spring_boot.spring_backend.entity.User;
 
@Repository
public interface UsersRepository extends JpaRepository<User,Long>{
 
}
