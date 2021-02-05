package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Categorie;

public interface ArticleService {
	
	Article saveArticle(Article p);
	
	Article updateArticle(Article p);
	
	void deleteArticle(Article p);
	
	void deleteArticleById(Long id);
	
	Article getArticle(Long id);
	
	List<Article> getAllArticles();
	
	List<Article> findByNomArticle(String nom); 
	
	List<Article> findByNomArticleContains(String nom); 
	
	List<Article> findByNomPrix (String nom, Double prix); 
	
	List<Article> findByCategorie (Categorie categorie); 
	
	List<Article> findByCategorieIdCat(Long id); 
	
	List<Article> findByOrderByNomArticleAsc(); 
	
	List<Article> trierArticlesNomsPrix();
	
	// Pagination
		Page<Article> getAllArticlesParPage(int page, int size);
}
