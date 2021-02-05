package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.marc.encheres_eni.entities.Categorie;
import fr.eni.ecole.marc.encheres_eni.entities.Enchere;
import fr.eni.ecole.marc.encheres_eni.repos.CategorieRepository;
import fr.eni.ecole.marc.encheres_eni.repos.EnchereRepository;

@Service
public class EnchereServiceImpl implements EnchereService {
	@Autowired
	private EnchereRepository enchereRepository; 
	
	
	@Override
	public List<Enchere> getAllEncheres() {
		return enchereRepository.findAll();
	}

	@Override
	public Enchere getEnchere(Long noEnchere) {
		return enchereRepository.getOne(noEnchere);
	}
	

}
