package com.hibernate.Model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Eleves {

	
	@Id
	@GeneratedValue
	@Column(name = "id_Eleves")
	private Integer id;
	private String nom;
	private String prenom;
	private String cp;
	private String ville;
	
	@OneToMany(mappedBy = "eleve", fetch = FetchType.EAGER)
	private List<Lacon> Lacons;

	public Eleves(Integer id, String nom, String prenom, String cp, String ville) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cp = cp;
		this.ville = ville;
	}
	
	


}
