package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.marc.encheres_eni.entities.Role;
import fr.eni.ecole.marc.encheres_eni.repos.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRole(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role findByNomRole(String nomRole) {
		return roleRepository.findByName(nomRole);
	}
	
	
}
