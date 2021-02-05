package fr.eni.ecole.marc.encheres_eni.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Categorie;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByNomArticle(String nomArticle);
	List<Article> findByNomArticleContains(String nomArticle);
	
	// Articles triés par nom (A-Z)
	List<Article> findByOrderByNomArticleAsc();
	
	//@Query("select p from Article p where p.nomArticle like %?1 and p.prixArticle > ?2") 
	//List<Article> findByNomPrix (String nom, Double prix);
	
	@Query("select a from Article a where a.nomArticle like %:nom and a.prixArticle > :prix") 
	List<Article> findByNomPrix (@Param("nom") String nom, @Param("prix") Double prix);
	

	@Query("select a from Article a order by a.nomArticle ASC, a.prixArticle DESC") 
	List<Article> trierArticlesByNomPrix ();
	
	@Query("select a from Article a where a.categorie = :categorie")
	List<Article> findByCategorie(@Param("categorie") Categorie categorie);
	
	List<Article> findByCategorieIdCat(Long id);
}
