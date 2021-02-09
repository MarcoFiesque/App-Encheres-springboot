package fr.eni.ecole.marc.encheres_eni.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
	@Valid
	@Column(nullable = false, length = 64, unique = true)
	private String 	username;

	@Column(nullable = false, length = 200)
	private String password;
	
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String 	nom;
	
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String 	prenom;
	
	@Column(nullable = false, unique = true)
	private String 	email;
	
	@Column(nullable = false, length = 20)
	private String 	telephone;
	
    @Column(columnDefinition = "integer default 100")
	private Integer credit;
	
	@Column(nullable=false, length = 200)
	private String rue;
	
	@Column(nullable=false, length = 200)
	private int code_postal;
	
	@Column(nullable=false, length = 200)
	private String ville;
	
	@Column
	private Boolean isAdmin = false;
	
	@OneToMany(mappedBy="vendeur")
	@ToString.Exclude
    private List<Article> articles;

	private Boolean enabled;
	
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER) 
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
	inverseJoinColumns = @JoinColumn(name="role_id")) 
	@ToString.Exclude
	private Set<Role> roles = new HashSet<>();
}