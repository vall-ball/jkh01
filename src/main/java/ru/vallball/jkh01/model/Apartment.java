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
	
	private double area;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
	
	private int howManyTenants;
	
	private int howManyRooms;

	public Apartment() {
		
	}

	public Apartment(@NotNull int number, @NotNull int entrance, @NotNull int level, @NotNull House house) {
		this.number = number;
		this.entrance = entrance;
		this.level = level;
		this.house = house;
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
