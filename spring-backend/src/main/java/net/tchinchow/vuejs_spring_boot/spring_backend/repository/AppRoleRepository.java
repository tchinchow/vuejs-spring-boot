package net.tchinchow.vuejs_spring_boot.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import net.tchinchow.vuejs_spring_boot.spring_backend.entity.AppRole;
 
@Repository
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

}
