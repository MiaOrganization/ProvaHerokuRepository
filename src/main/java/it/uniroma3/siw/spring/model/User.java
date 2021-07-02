package it.uniroma3.siw.spring.model;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;

	
	@Column(nullable=false)
	private String cognome;
	
	@Column(nullable=false)
	private String email;
	
	@Column
	private String cellulare;

	@lombok.experimental.Tolerate
	public User(String nome, String cognome,String cellulare) {
		this.nome = nome;
		this.cognome=cognome;

		this.cellulare=cellulare;
	}
	
	public User() {
		
	}
}
