package com.hibernate.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lacon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_Lacon")
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Eleves", nullable = false)
	private Eleves eleve;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Moniteur", nullable = false)
	private Moniteur moniteur;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Voiteur", nullable = false)

	private Voiteur voiteur;
	
	@Temporal(TemporalType.DATE)
	private LocalDate date_Lacon;

}
