package com.hibernate.Model;

import java.util.Date;
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

public class Moniteur {
	

	@Id
	@GeneratedValue
	@Column(name = "id_Moniteur")
	private Integer id;
	private String nom_Moniteur;
	private String prenom_Moniteur;
	private Date date_naissance;
	
	@OneToMany(mappedBy = "moniteur", fetch = FetchType.EAGER)
	private List<Lacon> Lacons;

	public Moniteur(Integer id, String nom_Moniteur, String prenom_Moniteur, Date date_naissance) {
		super();
		this.id = id;
		this.nom_Moniteur = nom_Moniteur;
		this.prenom_Moniteur = prenom_Moniteur;
		this.date_naissance = date_naissance;
	}
	
	

}
