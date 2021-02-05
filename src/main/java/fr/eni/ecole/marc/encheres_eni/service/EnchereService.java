package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import fr.eni.ecole.marc.encheres_eni.entities.Categorie;
import fr.eni.ecole.marc.encheres_eni.entities.Enchere;

public interface EnchereService {
	List<Enchere> getAllEncheres();
	
	Enchere getEnchere(Long noEnchere);	
}
