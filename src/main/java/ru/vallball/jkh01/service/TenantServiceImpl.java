package ru.vallball.jkh01.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.repository.TenantRepository;

@Service
@Transactional
public class TenantServiceImpl implements TenantService{
	
	static final Logger logger = LoggerFactory.getLogger(TenantService.class);
	
	@Autowired
	TenantRepository tenantRepository;
	
	@Autowired
	MyHardTenantValidator validator;

	@Override
	public void save(Tenant tenant) throws Exception {
		if (!validator.isUnique(tenant)) {
			throw new Exception("Such a tenant already in the list of the tenants!");
		}
		tenantRepository.save(tenant);
	}

	@Override
	public List<Tenant> list() {
		return tenantRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		tenantRepository.deleteById(id);
	}

	@Override
	public Tenant findById(Long id) {
		return tenantRepository.findById(id).get();
	}
	
	@Override
	public void update(Long id, Tenant tenant) throws Exception {
		Tenant tenantForUpdate = findById(id);
		if (!validator.isUnique(tenant)) {
			throw new Exception(
					"Such a tenant has already existed");
		}
		tenantForUpdate.setApartments(tenant.getApartments());
		tenantForUpdate.setLastname(tenant.getLastname());
		tenantForUpdate.setName(tenant.getName());
		tenantRepository.save(tenantForUpdate);
	}

	@Override
	public Tenant findByName(String name, String lastname) {
		Tenant tenant = tenantRepository.findByNameAndLastname(name, lastname);
		if (tenant == null) {
			throw new NoSuchElementException();
		}
		return tenant;
	}

	@Override
	public void deleteByName(String name, String lastname) {
		long l = tenantRepository.deleteByNameAndLastname(name, lastname);
		if (l == 0) {
			throw new EmptyResultDataAccessException((int) l);
		}
	}

	@Override
	public void update(String name, String lastname, Tenant tenant) throws Exception {
		Tenant tenantForUpdate = tenantRepository.findByNameAndLastname(name, lastname);
		if (tenantForUpdate == null) {
			throw new NoSuchElementException();
		}
		if (!validator.isUnique(tenantForUpdate)) {
			throw new Exception("Such a tenant has already existed");
		}
		tenantForUpdate.setApartments(tenant.getApartments());
		tenantForUpdate.setLastname(tenant.getLastname());
		tenantForUpdate.setName(tenant.getName());
		tenantRepository.save(tenantForUpdate);
	}

	@Override
	public void addApartment(Apartment apartment, Long id) {
		Tenant tenant = tenantRepository.findById(id).get();
		tenant.getApartments().add(apartment);
		tenantRepository.save(tenant);
		
	}

	@Override
	public void addApartments(Set<Apartment> apartments, Long id) {
		Tenant tenant = tenantRepository.findById(id).get();
		tenant.getApartments().addAll(apartments);
		tenantRepository.save(tenant);
		
	}

	@Override
	public void removeApartment(Apartment apartment, Long id) {
		Tenant tenant = tenantRepository.findById(id).get();
		for (Apartment a : tenant.getApartments()) {
			if (a.getId().equals(apartment.getId())) {
				tenant.getApartments().remove(a);
				break;
			}
		}
		tenantRepository.save(tenant);
		
	}
	
	
	
}
