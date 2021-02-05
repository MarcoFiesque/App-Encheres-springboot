package fr.eni.ecole.marc.encheres_eni.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArticle;
	
	@NotNull
	@Size (min = 3,max = 200)
	private String nomArticle;
	
	@NotNull
	@Size (min = 3,max = 300)
	private String decriptionArticle;
	
	@NotNull
	@Min(value = 10)
	@Max(value = 10000)
	private Double prixArticle;
	
	@Column(nullable = true)
	private Double prixArticleVendu;
	
	@Column(nullable = true)
	private Double etatVente;
	
	@Column(name="date_fin_enchere")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	private LocalDateTime dateFinEnchere;
	
	@Column(nullable = true, length = 64)
    private String imageArticle;

	@Transient
	public String getImageArticlePath() {
		if (imageArticle == null || idArticle == null)
			return null;

		return "/user-photos/" + idArticle + "/" + imageArticle;
	}
	
	@Column(name="created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private LocalDateTime updatedAt;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="categorieIdCat", nullable=false)
	private Categorie categorie;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="noEnchere", nullable=false)
	private Enchere enchere;
	

}
