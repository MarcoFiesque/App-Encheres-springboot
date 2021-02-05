package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;
import java.util.Optional;

import fr.eni.ecole.marc.encheres_eni.entities.Role;

public interface RoleService {
	
	List<Role> getAllRoles();
	
	Optional<Role> getRole(Long id);
	
	Role findByNomRole(String nomCat);
	
	
}
