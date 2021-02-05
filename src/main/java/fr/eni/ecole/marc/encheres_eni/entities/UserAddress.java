package fr.eni.ecole.marc.encheres_eni.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class UserAddress {
	@Column(nullable=true)
	private String rue;
	@Column(nullable=true)
	private int code_postal;
	@Column(nullable=true)
	private String ville;
}
