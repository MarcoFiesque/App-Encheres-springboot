package fr.eni.ecole.marc.encheres_eni.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ecole.marc.encheres_eni.entities.Enchere;
import fr.eni.ecole.marc.encheres_eni.entities.User;

public interface EnchereRepository extends JpaRepository<Enchere, Long>{
	List<Enchere> findEncheresByUser(User user);
}
