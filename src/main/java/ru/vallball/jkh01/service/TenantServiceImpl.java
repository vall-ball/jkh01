package ru.vallball.jkh01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.repository.TenantRepository;

@Service
@Transactional
public class TenantServiceImpl implements TenantService{
	
	@Autowired
	TenantRepository tenantRepository;

	@Override
	public void save(Tenant tenant) {
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
		return tenantRepository.getById(id);
	}

}
