package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.eni.ecole.marc.encheres_eni.entities.User;

public interface UserService {
	
	User saveUser(User u);
	
	List<User> getAllUsers();
	
	User getUser(Long id);
	
	User findByUsername(String name);
	
	User findByUsernameOrEmail(String key);
	
	List<User> findByOrderByUsername();
	
	Page<User> getAllUsersParPage(int page, int size);
	
	void deleteByIdUsers(Long id);
}
