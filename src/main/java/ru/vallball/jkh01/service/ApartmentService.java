package ru.vallball.jkh01.service;

import java.util.List;
import java.util.Set;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.Tenant;

public interface ApartmentService {

	void save(Apartment apartment) throws Exception;

	List<Apartment> list();

	void delete(Long id);

	void deleteByAddress(String street, String numberOfHouse, int numberOfApartment);

	Apartment findById(Long id);

	List<Apartment> listByHome(String street, String number);

	Apartment findByAddress(String street, String numberOfHouse, int numberOfApartment);

	void update(Long id, Apartment apartment) throws Exception;

	void update(String street, String numberOfHouse, int numberOfApartment, Apartment apartment) throws Exception;
	
	void addTenant(Tenant tenant, Long id);
	
	void addTenants(Set<Tenant> tenants, Long id);
	
	void removeTenant(Tenant tenant, Long id);

}
