package ru.vallball.jkh01.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "houses")
public class House {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String street;
	
	@NotNull
	private int number;
	
	@NotNull
	private int entrances;
	
	@NotNull
	private int levels;
	
	@NotNull
	private int apartmentsByLevel;
	
	@OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
	private List<Apartment> apartments;
	
	public House() {
		
	}
	
	public House(@NotNull String street, @NotNull int number, @NotNull int entrances, @NotNull int levels, @NotNull int apartmentsByLevel) {
		this.street = street;
		this.number = number;
		this.entrances = entrances;
		this.levels = levels;
		this.apartmentsByLevel = apartmentsByLevel;
		createApartments();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getEntrances() {
		return entrances;
	}

	public void setEntrances(int entrances) {
		this.entrances = entrances;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public List<Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Long getId() {
		return id;
	}

	public int getApartmentsByLevel() {
		return apartmentsByLevel;
	}

	public void setApartmentsByLevel(int apartmentsByLevel) {
		this.apartmentsByLevel = apartmentsByLevel;
	}
	
	public void createApartments() {
		List<Apartment> list = new ArrayList<>();
		int howManyApartmentsInHouse = this.getApartmentsByLevel() * this.getEntrances() * this.getLevels();
		int howManyApartmentsInEntrance = this.getApartmentsByLevel() * this.getLevels();
		for (int i = 1; i <= howManyApartmentsInHouse; i++) {
			int entrance = i / howManyApartmentsInEntrance + 1;
			int level = (i - (entrance - 1) * howManyApartmentsInEntrance) / this.getApartmentsByLevel() + 1;
			Apartment apartment = new Apartment(i, entrance, level, this);
			list.add(apartment);
		}
		this.apartments = list;
	}

}
