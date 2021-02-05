package fr.eni.ecole.marc.encheres_eni.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.eni.ecole.marc.encheres_eni.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	// Personnalisation de la requête afin de se connecter par username ou email
	@Query("SELECT u from User u WHERE u.username = ?1 OR u.email = ?1")
	User findByUsername(String username);
	
	@Query("SELECT u from User u WHERE u.username = ?1 OR u.email = ?1")
	User findByUsernameOrEmail(String key);
	
	List<User> findByOrderByUsername();
}
