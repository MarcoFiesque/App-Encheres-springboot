package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import fr.eni.ecole.marc.encheres_eni.entities.Categorie;

public interface CategorieService {
	List<Categorie> getAllCategories();
	
	Categorie getCategorie(Long id);
	
	Categorie findByNomCat(String nomCat);
}
