package ru.vallball.jkh01.service;

import java.util.List;

import ru.vallball.jkh01.model.Tenant;

public interface TenantService {
	
	void save(Tenant tenant) throws Exception;

	List<Tenant> list();

	void delete(Long id);

	Tenant findById(Long id);
	
	void update(Long id, Tenant tenant) throws Exception;
	
	public Tenant findByName(String name, String lastname);
	
	public void deleteByName(String name, String lastname);
	
	void update(String name, String lastname, Tenant tenant) throws Exception;

}
