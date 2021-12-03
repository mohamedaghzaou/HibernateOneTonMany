package com.hibernate.Model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lacon {
	
	@Id
	@GeneratedValue
	@Column(name = "id_Lacon")
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Eleves")
	private Eleves eleve;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_moniteur")

	private Moniteur moniteur;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Voiteur")
	private Voiteur voiteur;
	private Date date_Lacon;
	
	

	

}
