package fr.eni.ecole.marc.encheres_eni.service;

import org.springframework.beans.factory.annotation.Autowired;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Enchere;
import fr.eni.ecole.marc.encheres_eni.repos.EnchereRepository;

public class EnchereServiceImpl implements EnchereService{
	
	@Autowired
	EnchereRepository enchereRepository;

	@Override
	public Article findArticleByEnchereId(Long noEnchere) {
		return enchereRepository.findArticleByEnchereId(noEnchere);
	}

	@Override
	public Article getArticle(Article article) {
		return null;
	}

	@Override
	public Enchere findEnchereById(Long Id) {
		return enchereRepository.findByNoEnchere(Id);
	}

}
