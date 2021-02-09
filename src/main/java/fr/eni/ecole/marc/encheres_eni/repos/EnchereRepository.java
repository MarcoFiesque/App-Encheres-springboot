package fr.eni.ecole.marc.encheres_eni.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Enchere;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Long>{
	List<Enchere> findAllByNoEnchere(Long noEnchere);
	
	@Query("select p from Enchere p where p.noEnchere like %?1 ") 
	Article findArticleByEnchereId(Long noEnchere);
	
	Enchere findByNoEnchere(Long noEnchere);
	
	List<Enchere> findAll();
	
	
}
