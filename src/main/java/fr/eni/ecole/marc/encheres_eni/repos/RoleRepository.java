package fr.eni.ecole.marc.encheres_eni.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.eni.ecole.marc.encheres_eni.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	@Query("select p from Role p where p.name like %?1") 
	Role findByName(String name); 

}
