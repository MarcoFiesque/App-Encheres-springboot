package fr.eni.ecole.marc.encheres_eni.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	@Column(name = "no_enchere")
	private Long noEnchere;
	@Column
	private String descriptionCat;
	
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="noEnchere", referencedColumnName = "idArticle")
	private Article article;
	
	
}
