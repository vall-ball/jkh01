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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "houses", uniqueConstraints = {@UniqueConstraint(columnNames = {"street", "number"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class House {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "The street must be")
	private String street;
	
	@NotBlank(message = "The number must be")
	private String number;
	
	@Positive(message = "The number of entrances must be more zero")
	private int entrances;
	
	@Positive(message = "The number of levels must be more zero")
	private int levels;
	
	@Positive(message = "The number of apartments by level must be more zero")
	private int apartmentsByLevel;
	
	@OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Apartment> apartments;
	
	public House() {
		
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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
			int entrance = (i - 1) / howManyApartmentsInEntrance + 1;
			int level = (i - (entrance - 1) * howManyApartmentsInEntrance - 1) / this.getApartmentsByLevel() + 1;
			Apartment apartment = new Apartment();
			apartment.setEntrance(entrance);
			apartment.setHouse(this);
			apartment.setLevel(level);
			apartment.setNumber(i);
			list.add(apartment);
		}
		this.apartments = list;
	}

	@Override
	public String toString() {
		return "House [street=" + street + ", number=" + number + ", entrances=" + entrances + ", levels=" + levels
				+ ", apartmentsByLevel=" + apartmentsByLevel + "]";
	}
	
	

}
