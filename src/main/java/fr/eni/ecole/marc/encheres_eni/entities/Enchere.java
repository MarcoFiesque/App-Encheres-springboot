package fr.eni.ecole.marc.encheres_eni.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enchere")
public class Enchere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long noEnchere;
	private String descriptionCat;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "enchere")
	private List<Article> articles;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
}
