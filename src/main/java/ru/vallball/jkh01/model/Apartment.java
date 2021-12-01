package ru.vallball.jkh01.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "apartments", uniqueConstraints = {@UniqueConstraint(columnNames = {"house_id", "number"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Apartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive(message = "The number must be more zero")
	private int number;
	
	@Positive(message = "The number of entrance must be more zero")
	private int entrance;
	
	@Positive(message = "The number of level must be more zero")
	private int level;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "house_id", nullable = false)
	private House house;
	
	@PositiveOrZero(message = "The area must be not negative")
	private double area;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
	
	@PositiveOrZero(message = "The number of tenats must be not negative")
	private int howManyTenants;
	
	@PositiveOrZero(message = "The number of rooms must be not negative")
	private int howManyRooms;

	public Apartment() {
		//System.out.println("Apartment is creating");
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getEntrance() {
		return entrance;
	}

	public void setEntrance(int entrance) {
		this.entrance = entrance;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public int getHowManyTenants() {
		return howManyTenants;
	}

	public void setHowManyTenants(int howManyTenants) {
		this.howManyTenants = howManyTenants;
	}

	public int getHowManyRooms() {
		return howManyRooms;
	}

	public void setHowManyRooms(int howManyRooms) {
		this.howManyRooms = howManyRooms;
	}

	public Long getId() {
		return id;
	}
}
