package fr.eni.ecole.marc.encheres_eni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Categorie;
import fr.eni.ecole.marc.encheres_eni.repos.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Article saveArticle(Article a) {
		return articleRepository.save(a);
		 
	}

	@Override
	public Article updateArticle(Article a) {
		return articleRepository.save(a);
	}

	@Override
	public void deleteArticle(Article a) {
		articleRepository.delete(a);

	}

	@Override
	public void deleteArticleById(Long id) {
		articleRepository.deleteById(id);
	}

	@Override
	public Article getArticle(Long id) {
		return articleRepository.findById(id).get();
	}

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Page<Article> getAllArticlesParPage(int page, int size) {
		
		return articleRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Article> findByNomArticle(String nom) {
		return articleRepository.findByNomArticle(nom);
	}

	@Override
	public List<Article> findByNomArticleContains(String nom) {
		return articleRepository.findByNomArticleContains(nom);
	}

	@Override
	public List<Article> findByNomPrix(String nom, Double prix) {
		return articleRepository.findByNomPrix(nom, prix);
	}

	@Override
	public List<Article> findByCategorie(Categorie categorie) {
		return articleRepository.findByCategorie(categorie);
	}

	@Override
	public List<Article> findByCategorieIdCat(Long id) {
		return articleRepository.findByCategorieIdCat(id);
	}

	@Override
	public List<Article> findByOrderByNomArticleAsc() {
		return articleRepository.findByOrderByNomArticleAsc();
	}

	@Override
	public List<Article> trierArticlesNomsPrix() {
		return articleRepository.trierArticlesByNomPrix();
	}

}
