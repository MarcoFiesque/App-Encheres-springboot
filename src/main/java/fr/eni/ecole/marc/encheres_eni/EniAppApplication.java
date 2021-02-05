package fr.eni.ecole.marc.encheres_eni;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.service.ArticleService;

@SpringBootApplication
public class EniAppApplication implements CommandLineRunner{
	
	@Autowired
	ArticleService articleService;
	
	public static void main(String[] args) {
		SpringApplication.run(EniAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
