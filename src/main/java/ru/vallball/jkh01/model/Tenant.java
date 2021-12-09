package ru.vallball.jkh01.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tenants")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tenant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String lastname;

	@ManyToMany
	@JoinTable(
			  name = "apartments_tenants", 
			  joinColumns = @JoinColumn(name = "tenant_id"), 
			  inverseJoinColumns = @JoinColumn(name = "apartment_id"))
	private List<Apartment> apartments = new ArrayList<>();

	public Tenant() {

	}

	public Tenant(@NotNull String name, @NotNull String lastname) {
		this.name = name;
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Apartment> getApartment() {
		return apartments;
	}

	public void setApartment(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", name=" + name + ", lastname=" + lastname + "]";
	}

}
