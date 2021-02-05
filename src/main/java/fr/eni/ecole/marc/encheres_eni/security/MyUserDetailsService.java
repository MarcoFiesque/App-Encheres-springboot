package fr.eni.ecole.marc.encheres_eni.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.eni.ecole.marc.encheres_eni.entities.User;
import fr.eni.ecole.marc.encheres_eni.repos.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws
	UsernameNotFoundException {
			
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Utilisateur inconnu");
		}
		return new MyUserDetails(user);
	}

}