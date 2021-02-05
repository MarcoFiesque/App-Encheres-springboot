package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.eni.ecole.marc.encheres_eni.entities.User;
import fr.eni.ecole.marc.encheres_eni.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User u) {
		return userRepository.save(u);
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}


	@Override
	public List<User> findByOrderByUsername() {
		return userRepository.findByOrderByUsername();
	}

	@Override
	public User findByUsernameOrEmail(String key) {
		return userRepository.findByUsernameOrEmail(key);
	}

	@Override
	public Page<User> getAllUsersParPage(int page, int size) {
		
		return userRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public void deleteByIdUsers(Long id) {
		userRepository.deleteById(id);;
	}
	

}
