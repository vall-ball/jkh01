package ru.vallball.jkh01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.repository.TenantRepository;

@Component
public class MyHardTenantValidator {
	
	@Autowired
	TenantRepository tenantRepository;
	
	public boolean isUnique(Tenant value) {
		List<Tenant> tenants = tenantRepository.findAll();
		for (Tenant t : tenants) {
			if (t.getLastname().equals(value.getLastname()) && t.getName().equals(value.getName())) {
				return false;
			}
		}
		return true;
	}


}
