package ru.vallball.jkh01.service;

import java.util.List;

import ru.vallball.jkh01.model.Tenant;

public interface TenantService {
	
	void save(Tenant tenant);

	List<Tenant> list();

	void delete(Long id);

	Tenant findById(Long id);

}
