package fr.eni.ecole.marc;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ecole.marc.encheres_eni.EniAppApplication;
import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Categorie;
import fr.eni.ecole.marc.encheres_eni.repos.ArticleRepository;
import fr.eni.ecole.marc.encheres_eni.service.ArticleService;

@SpringBootTest(classes = EniAppApplication.class)
class EniAppApplicationTests {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleService articleService;
/*
	@Test
	public void testCreateArticle() {
		Article prod = new Article("MACBOOK PRO", 2700.900, new Date());
		ArticleRepository.save(prod);
	}

	@Test
	public void testFindByNomArticleContains() {
		Page<Article> articles = articleService.getAllArticlesParPage(0,2); 
		System.out.println(articles.getSize()); 
		System.out.println(articles.getTotalElements());
		System.out.println(articles.getTotalPages()); articles.getContent().forEach(article -> {System.out.println(article.toString());
		  });
  */
		   /*ou bien
		      for (Produit p : prods)
		{
		} */
	/*
	}
	@Test
	public List<Article> testFindByNomArticle(String nom) {
		List<Article> articles = ArticleRepository.findByNomArticle("Ps5");
		for(Article a : articles) {
			System.out.println(a);
			
		}
		return articles;
	}
	@Test
	public List<Article> testFindByNomArticleContains(String nom) {
		List<Article> articles = ArticleRepository.findByNomArticle("p");
		for(Article a : articles) {
			System.out.println(a);
			
		}
		return articles;
	}
	*/
	@Test
	public void testfindByNomPrix() {
	List<Article> articles = articleRepository.findByNomPrix("Macbook pro", (double) 1000); for (Article a : articles)
		{ 
			System.out.println(a);
		}
	}
	
	@Test
	public void testfindByCategorie() {
	Categorie cat = new Categorie(); 
	cat.setIdCat(1L);
	List<Article> prods = articleRepository.findByCategorie(cat );
	for (Article p : prods)
		{ 
	      System.out.println(p);
	    }
	}
}	
