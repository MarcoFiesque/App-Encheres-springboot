package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ecole.marc.encheres_eni.entities.Categorie;
import fr.eni.ecole.marc.encheres_eni.repos.CategorieRepository;

@Service
public class CategorieServiceImpl implements CategorieService {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Override
	public List<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getCategorie(Long id) {
		return categorieRepository.getOne(id);
	}

	@Override
	public Categorie findByNomCat(String nomCat) {
		return categorieRepository.findByNomCat(nomCat);
	}

}
