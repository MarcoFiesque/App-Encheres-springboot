package fr.eni.ecole.marc.encheres_eni.service;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Enchere;

public interface EnchereService {
	Enchere findEnchereById(Long Id);
	Article findArticleByEnchereId(Long noEnchere);
	Article getArticle(Article article);
}
