package ru.vallball.jkh01.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "apartments")
public class Apartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int number;
	
	@NotNull
	private int entrance;
	
	@NotNull
	private int level;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "house_id", nullable = false)
	private House house;
}
