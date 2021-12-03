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
public class Voiteur {
	
	@Id
	@GeneratedValue
	@Column(name = "id_Voiteur")
	private Integer id;
	private String Model;
	private String coleur;
	private String adresse;
	private Date date_mise_circulation;
	
	@OneToMany(mappedBy = "voiteur", fetch = FetchType.EAGER)
	private List<Lacon> Lacons;

	public Voiteur(Integer id, String model, String coleur, String adresse, Date date_mise_circulation) {
		super();
		this.id = id;
		Model = model;
		this.coleur = coleur;
		this.adresse = adresse;
		this.date_mise_circulation = date_mise_circulation;
	}
	
}
