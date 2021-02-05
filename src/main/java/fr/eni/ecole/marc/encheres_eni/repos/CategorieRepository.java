package fr.eni.ecole.marc.encheres_eni.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ecole.marc.encheres_eni.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long>{
	Categorie findByNomCat(String nomCat); 
	List<Categorie> findByOrderByNomCatAsc();
}
