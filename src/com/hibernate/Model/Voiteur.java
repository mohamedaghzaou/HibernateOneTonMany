package com.hibernate.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voiteur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Voiteur")
	private Integer id;
	private String Model;
	private String coleur;
	private String adresse;
	@Temporal(TemporalType.DATE)
	private Date date_mise_circulation;

	@OneToMany(mappedBy = "voiteur", fetch = FetchType.LAZY)
	@ToString.Exclude
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
